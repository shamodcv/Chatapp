package Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientFormController {
    public ScrollPane scrollPain;
    public Text txtLabel;
    public JFXTextField txtMsg;
    public VBox vBox;
    @FXML
    private TextArea txtFClient;
    @FXML
    private TextField txtClient;
    @FXML
    private AnchorPane ClientPane;

    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message ="";

    public void initialize(){
        new Thread(() -> {
            try {
                socket = new Socket("localhost" ,4002);
                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream=new DataOutputStream(socket.getOutputStream());
                while (!message.equals("exit")){
                    message=dataInputStream.readUTF();
                    txtFClient.appendText("\nServer: "+message);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }

    public void SendButtonOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtClient.getText().trim());
        dataOutputStream.flush();
    }

    public void txtMsgOnAction(ActionEvent actionEvent) {
    }

    public void sendButtonOnAction(ActionEvent actionEvent) {
    }
}
