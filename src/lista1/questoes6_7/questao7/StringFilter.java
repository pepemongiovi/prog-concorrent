package lista1.questoes6_7.questao7;

import lista1.questoes6_7.channel.Channel;

/**
 * Gets the strings puts in the {@link Channel}, and adds the alpha string into filtered {@link Channel}.
 *
 * @author Thaynan Nunes
 */
public class StringFilter implements Runnable {

    private final Channel<String> producerChannel;
    private final Channel<String> filteredChannel;

    public StringFilter(Channel producerChannel, Channel filteredChannel) {
        this.producerChannel = producerChannel;
        this.filteredChannel = filteredChannel;
    }

    /**
     * Verifies and returns {@code true} if is contains Number, {@code false} otherwise.
     *
     * @param alphanumberString String to be verify
     * @return {@code true} if is contains Number, {@code false} otherwise
     */
    private boolean containsNumber(String alphanumberString) {
        return alphanumberString.matches(".*\\d+.*");
    }

    @Override
    public void run() {
        while (!this.producerChannel.isClosed() || !this.producerChannel.isEmpty()) {
            try {
                String alphanumberString = this.producerChannel.take();
                // It's necessary to check if it is null because the take operation may be waiting for an element to be
                // inserted and the channel to be closed.
                if(alphanumberString != null && !containsNumber(alphanumberString)) {
                    this.filteredChannel.put(alphanumberString);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // After filtered all strings in producerChannel, It's necessary close the channel filteredChannel,
        // because It's not necessary insert more elements.
        this.filteredChannel.closeChannel();
    }
}