package com.example.alfa_monitor;

import android.content.Context;
import android.graphics.Region;

public class SQSMessageReceiverBuilder {
    private String queueUrl;
    private Region region;
    private Context context;

    public SQSMessageReceiverBuilder setQueueUrl(String queueUrl) {
        this.queueUrl = queueUrl;
        return this;
    }

    public SQSMessageReceiverBuilder setRegion(Region region) {
        this.region = region;
        return this;
    }

    public SQSMessageReceiverBuilder setContext(Context context) {
        this.context = context;
        return this;
    }

    public SQSMessageReceiver createSQSMessageReceiver() {
        return new SQSMessageReceiver(queueUrl, region, context);
    }
}