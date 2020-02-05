package spatutorial.client.modules

import diode.data.Pot
import diode.react.ReactPot._
import diode.react._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import spatutorial.client.components.ReactBootstrap._
import spatutorial.client.components._
import spatutorial.client.logger._
import spatutorial.client.services._
import spatutorial.shared._

object Todo {

  case class Props(proxy: ModelProxy[Pot[Todos]])

  case class State(selectedItem: Option[TodoItem] = None, showTodoForm: Boolean = false)

  class Backend($: BackendScope[Props, State]) {
    def mounted(props: Props) =
    // dispatch a message to refresh the todos, which will cause TodoStore to fetch todos from the server
      Callback.when(props.proxy().isEmpty)(props.proxy.dispatchCB(RefreshTodos))

    def hideTodoEditor =
      $.modState(s => s.copy(showTodoForm = false))

    def editTodo(item: Option[TodoItem]) = {
      // activate the edit dialog
      $.modState(s => s.copy(selectedItem = item, showTodoForm = true))
    }

    def editCancelled = {
      Callback.log("Todo editing cancelled") >> hideTodoEditor
    }

    def editSubmitted(item: TodoItem) = {
      Callback.log(s"Todo edited: $item") >>
        ($.props >>= (_.proxy.dispatchCB(UpdateTodo(item)))) >>
        hideTodoEditor
    }

    def render(p: Props, s: State) = {
      Panel()(
        PanelHeading()("What needs to be done"),
        PanelBody()(
        p.proxy().renderFailed(ex => "Error loading"),
        p.proxy().renderPending(_ > 500, _ => "Loading..."),
        p.proxy().render(todos => TodoList(todos.items, item => p.proxy.dispatchCB(UpdateTodo(item)),
          item => editTodo(Some(item)), item => p.proxy.dispatchCB(DeleteTodo(item)))),
          Button(onClick = editTodo(None).toScalaFn)(Icon.plusSquare, " New")
        ),
        // if the dialog is open, add it to the panel
        if (s.showTodoForm)
          TodoForm(TodoForm.Props(s.selectedItem, editSubmitted, editCancelled)): VdomNode
        else // otherwise add an empty placeholder
          VdomArray.empty()
      )
    }
  }

  // create the React component for To Do management
  val component = ScalaComponent.builder[Props]("TODO")
    .initialState(State()) // initial state from TodoStore
    .renderBackend[Backend]
    .componentDidMount(scope => scope.backend.mounted(scope.props))
    .build

  /** Returns a function compatible with router location system while using our own props */
  def apply(proxy: ModelProxy[Pot[Todos]]) = component(Props(proxy))
}

object TodoForm {
  // shorthand for styles
  @inline private def bss = GlobalStyles.bootstrapStyles

  case class Props(item: Option[TodoItem], onSubmit: TodoItem => Callback, onCancel: Callback)

  case class State(item: TodoItem, cancelled: Boolean = true)

  class Backend(t: BackendScope[Props, State]) {

    def updateDescription(e: ReactEventFromInput) = {
      val text = e.target.value
      // update TodoItem content
      t.modState(s => s.copy(item = s.item.copy(content = text)))
    }

    def updatePriority(e: ReactEventFromInput) = {
      // update TodoItem priority
      val newPri = e.currentTarget.value match {
        case p if p == TodoHigh.toString => TodoHigh
        case p if p == TodoNormal.toString => TodoNormal
        case p if p == TodoLow.toString => TodoLow
      }
      t.modState(s => s.copy(item = s.item.copy(priority = newPri)))
    }

    def render(p: Props, s: State) = {
      log.debug(s"User is ${if (s.item.id == "") "adding" else "editing"} a todo or two")
      val headerText = if (s.item.id == "") "Add new todo" else "Edit todo"
      Modal(show = true, backdropClassName = "fade")(
        ModalHeader()(
          <.span(
            Button(tpe = "button", onClick = p.onCancel.toScalaFn)(^.className := "close", Icon.close),
            <.h4(headerText))),
        ModalBody()(
          FormGroup(controlId = "description")(
            ControlLabel()("Description"),
            FormControl()(^.placeholder := "write description", ^.autoComplete := "off",
              ^.value := s.item.content, ^.onChange ==> updateDescription)),
          FormGroup(controlId = "priority")(
            ControlLabel()("Priority"),
            FormControl("select")(^.value := s.item.priority.toString, ^.onChange ==> updatePriority,
              <.option(^.value := TodoHigh.toString, "High"),
              <.option(^.value := TodoNormal.toString, "Normal"),
              <.option(^.value := TodoLow.toString, "Low")))),
        ModalFooter()(
          Button(onClick = p.onSubmit(s.item).toScalaFn)("OK")))
    }
  }

  val component = ScalaComponent.builder[Props]("TodoForm")
    .initialStateFromProps(p => State(p.item.getOrElse(TodoItem("", 0, "", TodoNormal, completed = false))))
    .renderBackend[Backend]
    .build

  def apply(props: Props) = component(props)
}