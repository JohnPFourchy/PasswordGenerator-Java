
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;

public class Controller implements Initializable {
    @FXML
    private Button copyButton;
    @FXML
    private Label entropyLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private CheckBox lowercaseCheckBox;
    @FXML
    private Spinner<Integer> numberSpinner;
    @FXML
    private CheckBox numbersCheckBox;
    @FXML
    private Label passwordLabel;
    @FXML
    private CheckBox symbolsCheckBox;
    @FXML
    private CheckBox uppercaseCheckBox;

    // Instance of the model
    private Model model = new Model();

    @FXML
    void GenerateOnClick(ActionEvent event) {
        int len = numberSpinner.getValue();
        if(!model.checkForErrors(len, errorLabel)) {
            return;
        } else {
            model.createPassword(len, errorLabel);
            model.showPassword(passwordLabel);
            model.showCopyButton(copyButton);
            model.showEntropyLabel(entropyLabel, passwordLabel.getText());
        }
    }

    @FXML
    void copyButtonClicked(ActionEvent event) {
        model.copyToClipboard(passwordLabel);
    }

    @FXML
    void lowercaseChecked(ActionEvent event) {
        if(lowercaseCheckBox.isSelected()) {
            model.setLowercase(true);
        } else {
            model.setLowercase(false);
        }
    }

    @FXML
    void numbersChecked(ActionEvent event) {
        if(numbersCheckBox.isSelected()) {
            model.setNumber(true);
        } else {
            model.setNumber(false);
        }
    }

    @FXML
    void symbolsChecked(ActionEvent event) {
        if(symbolsCheckBox.isSelected()) {
            model.setSymbol(true);
        } else {
            model.setSymbol(false);
        }
    }

    @FXML
    void uppercaseChecked(ActionEvent event) {
        if(uppercaseCheckBox.isSelected()) {
            model.setUppercase(true);
        } else {
            model.setUppercase(false);
        }
    }

    /*
     * Sets up the number spinner with a default value of 12, minimum value of 1, and maximum value of 64
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 64);

        // Set default value
        valueFactory.setValue(6);
        numberSpinner.setValueFactory(valueFactory);

        // Set the copyButton graphic
        copyButton.setGraphic(new ImageView("Images/clipboard.png"));
    }

}
