package no.hvl.dat110.fsms;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import no.hvl.dat110.stopablethreads.*;

public class Transmitter extends Stopable {

	private States state;
	private LinkedBlockingQueue<Events> eventqueue;

	private Receiver receiver;

	public Transmitter(Receiver receiver) {
		super("Transmitter");
		this.state = States.CLOSED;
		this.receiver = receiver;
		eventqueue = new LinkedBlockingQueue<Events>();
	}

	// events to this protocol entity
	public void do_open() {
		eventqueue.add(Events.DO_OPEN);
	}

	public void do_send() {
		eventqueue.add(Events.DO_SEND);
	}

	public void do_close() {
		eventqueue.add(Events.DO_CLOSE);
	}

	private Events getNextEvent() {

		Events event = null;

		try {

			event = eventqueue.poll(2, TimeUnit.SECONDS);

		} catch (InterruptedException ex) {
			System.out.println("Transmitter - doProcess " + ex.getMessage());
			ex.printStackTrace();
		}

		return event;
	}

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

	public void doClosed() {

		Events event = getNextEvent();

		if (event != null) {

			System.out.println("Transmitter[" + state + "]" + "(" + event + ")");
			switch (event) {
			case DO_OPEN:
				send_open();
				state = States.OPEN;
				System.out.println("Transmitter -> OPEN");
				break;
			default:
				break;
			}
		}
	}

	public void doOpen() {

		Events event = getNextEvent();

		if (event != null) {

			System.out.println("Transmitter[" + state + "]" + "(" + event + ")");
			
			switch (event) {
			case DO_SEND:
				send_data();
				break;
			case DO_CLOSE:
				send_close();
				state = States.CLOSED;
				System.out.println("Transmitter -> CLOSED");
				break;
			default:
				break;
			}
		}
	}

	// actions to this protocol entity
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
