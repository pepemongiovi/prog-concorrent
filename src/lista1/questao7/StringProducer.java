package lista1.questao7;

import lista1.channel.Channel;

import java.util.Random;

public class StringProducer implements Runnable {

    private final String ALPHANUM = "abcdefghijklmnopqrstuvxywz0123456789";
    private final Channel<String> channel;

    public StringProducer(Channel channel) {
        this.channel = channel;
    }

    public String generateRandomAlphanumString() {
        Random random = new Random();

        Integer alphanumStringLength = random.nextInt(20);
        // Avoid empty string
        alphanumStringLength = alphanumStringLength == 0 ? 1 : alphanumStringLength;

        String alphanumString = "";
        for (int i=0; i < alphanumStringLength; i++) {
            Integer index = random.nextInt(ALPHANUM.length());
            alphanumString += ALPHANUM.charAt(index);
        }
        return alphanumString;
    }

    @Override
    public void run() {
        for (int i=0; i < 10; i++) {
            String alphanumString = this.generateRandomAlphanumString();
            this.channel.putMessage(alphanumString);
        }
    }
}