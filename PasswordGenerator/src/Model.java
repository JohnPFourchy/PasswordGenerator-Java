
import java.security.SecureRandom;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class Model {

    private StringBuilder password;
    private StringBuilder entireSelection;
    private boolean includeLowercase;
    private boolean includeUppercase;
    private boolean includeNumber;
    private boolean includeSymbol;

    public Model() {
        this.password = new StringBuilder();
        this.entireSelection = new StringBuilder();
    }

    public void showPassword(Label label) {
        label.setText(password.toString());
        label.setVisible(true);
    }

    public void createPassword(int length, Label error) {
        SecureRandom random = new SecureRandom();

        password.setLength(0);
        for(int i = 0; i < length; i++) {
            // Create new random char and add to password
            int randInt = random.nextInt(entireSelection.length());
            password.append(entireSelection.charAt(randInt));
        }
    }

    public boolean checkForErrors(int pwLength, Label errorLabel) {
        if(entireSelection.length() == 0) {
            errorLabel.setText("At least one checkbox must be selected.");
            errorLabel.setVisible(true);
            return false;
        }
        errorLabel.setVisible(false);
        return true;
    }

    private void showEmptyErrorMessage(Label errorLabel, String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    public String calculateEntropy(String password) {
        // Formula: entropy = passwordLength * log_2(sizeOfUniqueCharacterPool)
        int pwLength = password.length();
        int ucSize = entireSelection.toString().length();
        double entropy = pwLength * Math.log(ucSize) / Math.log(2);

        return String.format("%.2f", entropy);
    }

    public void showEntropyLabel(Label enLabel, String password) {
        String entropy = calculateEntropy(password);
        enLabel.setText("Entropy:  " + entropy + " bits");
        enLabel.setVisible(true);
    }

    public void showCopyButton(Button button) {
        button.setVisible(true);
    }

    public void copyToClipboard(Label label) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(label.getText());
        clipboard.setContent(content);
    }

    public void setLowercase(boolean val) {
        this.includeLowercase = val;
        if(val == true) {
            entireSelection.append("abcdefghijklmnopqrstuvwxyz");
        } else {
            int start = entireSelection.indexOf("a");
            entireSelection.delete(start, start + 26);
        }
    }

    public void setUppercase(boolean val) {
        this.includeUppercase = val;
        if(val == true) {
            entireSelection.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        } else {
            int start = entireSelection.indexOf("A");
            entireSelection.delete(start, start + 26);
        }
    }

    public void setNumber(boolean val) {
        this.includeNumber = val;
        if(val == true) {
            entireSelection.append("0123456789");
        } else {
            int start = entireSelection.indexOf("0");
            entireSelection.delete(start, start + 10);
        }
    }

    public void setSymbol(boolean val) {
        this.includeSymbol = val;
        if(val == true) {
            entireSelection.append("`~!@#$%^&*()-=_+[{]}\\|;':\",.<>/?");
        } else {
            int start = entireSelection.indexOf("`");
            entireSelection.delete(start, start + 32);
        }
    }
}
