package com.example.alfa_monitor;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Region;
import android.os.Message;

import java.util.List;

public class SQSMessageReceiver {

    private final String queueUrl;
    private final SqsClient sqsClient;
    private final Context context;

    public SQSMessageReceiver(String queueUrl, Region region, Context context) {
        this.queueUrl = queueUrl;
        this.sqsClient = SqsClient.builder()
                .region("us-east-1")
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
        this.context = context;
    }

    public void startReceivingMessages() {
        ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .build();

        while (true) {
            ReceiveMessageResponse response = sqsClient.receiveMessage(request);
            List<Message> messages = response.messages();

            for (Message message : messages) {
                String messageBody = message.body();
                // Process the received message as per your application's logic
                displayMessagePopup(messageBody);
            }
        }
    }

    private void displayMessagePopup(String messageContent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Message Received");
        builder.setMessage(messageContent);
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }
}
