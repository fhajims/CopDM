import android.app.Dialog
import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.DialogFragment
import at.fhjoanneum.ims23.mosodev.dicemaster.MyComposeDialog

class MyDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(ComposeView(requireContext()).apply {
            setContent {
                MyComposeDialog {
                    dialog.dismiss()
                }
            }
        })
        return dialog
    }
}