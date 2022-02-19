package no.hvl.dat110.fsms;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import no.hvl.dat110.stopablethreads.*;

public class Transmitter extends Stopable {

	private FSMState state;
	private LinkedBlockingQueue<TransmitterEvent> eventqueue;

	private Receiver receiver;

	public Transmitter(Receiver receiver) {
		super("Transmitter");
		this.state = FSMState.CLOSED;
		this.receiver = receiver;
		eventqueue = new LinkedBlockingQueue<TransmitterEvent>();
	}

	// events to be processed by this protocol entity
	public void do_open() {
		eventqueue.add(TransmitterEvent.DO_OPEN);
	}

	public void do_send() {
		eventqueue.add(TransmitterEvent.DO_SEND);
	}

	public void do_close() {
		eventqueue.add(TransmitterEvent.DO_CLOSE);
	}

	// fetch event from the event queue (if any)
	private TransmitterEvent getNextEvent() {

		TransmitterEvent event = null;

		try {

			event = eventqueue.poll(2, TimeUnit.SECONDS);

		} catch (InterruptedException ex) {
			System.out.println("Transmitter - doProcess " + ex.getMessage());
			ex.printStackTrace();
		}

		return event;
	}

	// main processing loop
	public void doProcess() {

		switch (state) {

		case CLOSED:
			doClosed();
			break;

		case OPEN:
			doOpen();
			break;

		default:
			break;
		}
	}

	// processing in the Closed state
	public void doClosed() {

		TransmitterEvent event = getNextEvent();

		if (event != null) {

			System.out.println("Transmitter[" + state + "]" + "(" + event + ")");
			switch (event) {
			case DO_OPEN:
				send_open();
				state = FSMState.OPEN;
				System.out.println("Transmitter: CLOSED -> OPEN");
				break;
				
			default:
				break;
			}
		}
	}

	// processing in the Open state
	public void doOpen() {

		TransmitterEvent event = getNextEvent();

		if (event != null) {

			System.out.println("Transmitter[" + state + "]" + "(" + event + ")");
			
			switch (event) {
			case DO_SEND:
				send_data();
				break;
				
			case DO_CLOSE:
				send_close();
				state = FSMState.CLOSED;
				System.out.println("Transmitter: OPEN -> CLOSED");
				break;
				
			default:
				break;
			}
		}
	}

	// actions to that this protocol entity may perform
	public void send_open() {
		receiver.recv_open();
	}

	public void send_data() {
		receiver.recv_data();
	}

	public void send_close() {
		receiver.recv_close();
	}

}
