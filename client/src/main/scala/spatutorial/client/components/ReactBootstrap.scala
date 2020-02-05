package spatutorial.client.components

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
import org.scalajs.dom.Event

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.JSImport

object ReactBootstrap {

  object Button extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/Button", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(tpe: UndefOr[String] = js.undefined,
              bsStyle: UndefOr[String] = js.undefined,
              bsSize: UndefOr[String] = js.undefined,
              active: UndefOr[Boolean] = js.undefined,
              block: UndefOr[Boolean] = js.undefined,
              disabled: UndefOr[Boolean] = js.undefined,
              href: UndefOr[String] = js.undefined,
              onClick: UndefOr[() => Unit] = js.undefined): WithProps = auto
  }

  object Checkbox extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/Checkbox", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(inline: UndefOr[Boolean] = js.undefined,
              disabled: UndefOr[Boolean] = js.undefined,
              title: UndefOr[String] = js.undefined,
              validationState: UndefOr[String] = js.undefined,
              inputRef: UndefOr[String] = js.undefined): WithProps = auto
  }

  object ControlLabel extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/ControlLabel", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(htmlFor: UndefOr[String] = js.undefined,
              srOnly: UndefOr[Boolean] = js.undefined): WithProps = auto
  }

  object Dialog extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/ModalDialog", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(dialogClassName: UndefOr[String] = js.undefined,
              bsSize: UndefOr[String] = js.undefined): WithProps = auto
  }

  object Form extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/Form", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(horizental: UndefOr[Boolean] = js.undefined,
              inline: UndefOr[Boolean] = js.undefined,
              componentClass: UndefOr[String] = js.undefined): WithProps = auto
  }

  object FormControl extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/FormControl", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(componentClass: UndefOr[String] = js.undefined,
              tpe: UndefOr[String] = js.undefined,
              id: UndefOr[String] = js.undefined,
              inputRef: UndefOr[String] = js.undefined,
              bsSize: UndefOr[String] = js.undefined): WithProps = auto
  }

  object FormControlFeedback extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/FormControlFeedback", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply: WithProps = auto
  }

  object FormControlStatic extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/FormControlStatic", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(componentClass: UndefOr[String] = js.undefined): WithProps = auto
  }

  object FormGroup extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/FormGroup", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(controlId: UndefOr[String] = js.undefined,
              validationState: UndefOr[String] = js.undefined,
              bsSize: UndefOr[String] = js.undefined): WithProps = auto
  }

  object HelpBlock extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/HelpBlock", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply: WithProps = auto
  }

  object InputGroup extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/InputGroup", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(bsSize: UndefOr[String] = js.undefined): WithProps = auto
  }

  object InputGroupAddon extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/InputGroupAddon", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply: WithProps = auto
  }

  object InputGroupButton extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/InputGroupButton", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply: WithProps = auto
  }

  object Modal extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/Modal", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(backdrop: UndefOr[js.Any] = js.undefined,
              backdropClassName: UndefOr[String] = js.undefined,
              keyboard: UndefOr[Boolean] = js.undefined,
              animation: UndefOr[Boolean] = js.undefined,
              autoFocus: UndefOr[Boolean] = js.undefined,
              enforceFocus: UndefOr[Boolean] = js.undefined,
              restoreFocus: UndefOr[Boolean] = js.undefined,
              show: UndefOr[Boolean] = js.undefined,
              bsSize: UndefOr[String] = js.undefined): WithProps = auto
  }

  object ModalBody extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/ModalBody", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(): WithProps = auto
  }

  object ModalFooter extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/ModalFooter", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(): WithProps = auto
  }

  object ModalHeader extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/ModalHeader", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(closeLabel: UndefOr[String] = js.undefined,
              closeButton: UndefOr[Boolean] = js.undefined): WithProps = auto
  }

  object Panel extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/Panel", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(bsStyle: UndefOr[String] = js.undefined,
              expanded: UndefOr[Boolean] = js.undefined,
              onToggle: UndefOr[(Boolean, Event) => Unit] = js.undefined,
              //              eventKey: UndefOr[Any] = js.undefined,
              id: UndefOr[String] = js.undefined): WithProps = auto
  }

  object PanelBody extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/PanelBody", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(collapsible: UndefOr[Boolean] = false): WithProps = auto
  }

  object PanelHeading extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/PanelHeading", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(): WithProps = auto
  }

  object Radio extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/Radio", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue: js.Any = RawComponent

    def apply(inline: UndefOr[Boolean] = js.undefined,
              disabled: UndefOr[Boolean] = js.undefined,
              title: UndefOr[String] = js.undefined,
              validationState: UndefOr[String] = js.undefined,
              inputRef: UndefOr[String] = js.undefined): WithProps = auto
  }
}