package spatutorial.client.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import spatutorial.shared._
import scalacss.ScalaCssReact._
import spatutorial.client.components.BootstrapStyles.CommonStyle
import spatutorial.client.components.ReactBootstrap.Button

object TodoList {
  // shorthand for styles
  @inline private def bss = GlobalStyles.bootstrapStyles

  case class TodoListProps(
    items: Seq[TodoItem],
    stateChange: TodoItem => Callback,
    editItem: TodoItem => Callback,
    deleteItem: TodoItem => Callback
  )

  private val TodoList = ScalaComponent.builder[TodoListProps]("TodoList")
    .render_P(p => {
      val style = bss.listGroup
      def renderItem(item: TodoItem) = {
        // convert priority into Bootstrap style
        val itemStyle = item.priority match {
          case TodoLow => style.itemOpt(CommonStyle.info)
          case TodoNormal => style.item
          case TodoHigh => style.itemOpt(CommonStyle.danger)
        }
        <.li(itemStyle,
          <.input.checkbox(^.checked := item.completed, ^.onChange --> p.stateChange(item.copy(completed = !item.completed))),
          <.span(" "),
          if (item.completed) <.s(item.content) else <.span(item.content),
          Button(onClick = p.editItem(item).toScalaFn)(^.className := "pull-right btn-xs", "Edit"),
          Button(onClick = p.deleteItem(item).toScalaFn)(^.className := "pull-right btn-xs", "Delete")
        )
      }
      <.ul(style.listGroup)(p.items toTagMod renderItem)
    })
    .build

  def apply(items: Seq[TodoItem], stateChange: TodoItem => Callback, editItem: TodoItem => Callback, deleteItem: TodoItem => Callback) =
    TodoList(TodoListProps(items, stateChange, editItem, deleteItem))
}
