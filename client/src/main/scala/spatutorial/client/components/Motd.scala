package spatutorial.client.components

import diode.data.Pot
import diode.react.ReactPot._
import diode.react._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import spatutorial.client.components.ReactBootstrap._
import spatutorial.client.services.UpdateMotd

/**
  * This is a simple component demonstrating how to display async data coming from the server
  */
object Motd {

  // create the React component for holding the Message of the Day
  val Motd = ScalaComponent.builder[ModelProxy[Pot[String]]]("Motd")
    .render_P { proxy =>
      Panel()(
        PanelHeading()("Message of the day"),
        PanelBody()(
          // render messages depending on the state of the Pot
          proxy().renderPending(_ > 500, _ => <.p("Loading...")),
          proxy().renderFailed(ex => <.p("Failed to load")),
          proxy().render(m => <.p(m)),
          Button(onClick = proxy.dispatchCB(UpdateMotd()).toScalaFn, bsStyle = "danger")(Icon.refresh, " Update")
        )
      )
    }
    .componentDidMount(scope =>
      // update only if Motd is empty
      Callback.when(scope.props.value.isEmpty)(scope.props.dispatchCB(UpdateMotd()))
    )
    .build

  def apply(proxy: ModelProxy[Pot[String]]) = Motd(proxy)
}
