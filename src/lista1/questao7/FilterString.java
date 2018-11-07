package lista1.questao7;

import lista1.channel.Channel;

public class FilterString implements Runnable {

    private final Channel<String> producerChannel;
    private final Channel<String> filteredChannel;

    public FilterString(Channel producerChannel, Channel filteredChannel) {
        this.producerChannel = producerChannel;
        this.filteredChannel = filteredChannel;
    }

    private boolean containsNumber(String alphanumberString) {
        return alphanumberString.matches(".*\\d+.*");
    }

    @Override
    public void run() {
        for (int i=0; i < 10; i++) {
            String alphanumberString = this.producerChannel.takeMessage();
            if(!containsNumber(alphanumberString)) {
                this.filteredChannel.putMessage(alphanumberString);
            }
        }
    }
}