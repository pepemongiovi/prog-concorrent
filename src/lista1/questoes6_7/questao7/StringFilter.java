package lista1.questoes6_7.questao7;

import lista1.questoes6_7.channel.Channel;

public class StringFilter implements Runnable {

    private final Channel<String> producerChannel;
    private final Channel<String> filteredChannel;

    public StringFilter(Channel producerChannel, Channel filteredChannel) {
        this.producerChannel = producerChannel;
        this.filteredChannel = filteredChannel;
    }

    private boolean containsNumber(String alphanumberString) {
        return alphanumberString.matches(".*\\d+.*");
    }

    @Override
    public void run() {
        while (!this.producerChannel.isClosed()) {
            try {
                String alphanumberString = this.producerChannel.take();
                if(alphanumberString != null && !containsNumber(alphanumberString)) {
                    this.filteredChannel.put(alphanumberString);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            this.filteredChannel.closeChannel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}