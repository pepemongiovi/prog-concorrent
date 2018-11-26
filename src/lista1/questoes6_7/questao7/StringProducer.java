package lista1.questoes6_7.questao7;

import lista1.questoes6_7.channel.Channel;

import java.util.Random;

/**
 * Producer aplhanumber strings and insert into Producer {@link Channel}.
 *
 * @author Thaynan Nunes
 */
public class StringProducer implements Runnable {

    private final String ALPHANUM = "abcdefghijklmnopqrstuvxywz0123456789";
    private final Channel<String> channel;

    public StringProducer(Channel channel) {
        this.channel = channel;
    }

    /**
     * Generates and returns random string that can contains numbers or letters.
     *
     * @return String generated
     */
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
        for (int i=0; i < 30; i++) {
            String alphanumString = this.generateRandomAlphanumString();
            this.channel.put(alphanumString);
        }
        this.channel.closeChannel();
    }
}