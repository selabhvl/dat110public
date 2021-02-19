package no.hvl.dat110.fsms;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import no.hvl.dat110.stopablethreads.Stopable;

public class Receiver extends Stopable {

	private States state;
	private LinkedBlockingQueue<Events> eventqueue;
	
	public Receiver() {
		super("Receiver");
		state = States.CLOSED;
		eventqueue = new LinkedBlockingQueue<Events>();
	}
	
	// events on this protocol entity
	public void recv_open() {
		eventqueue.add(Events.RECV_OPEN);
	}
	
	public void recv_data() {
		eventqueue.add(Events.RECV_DATA);
	}
	
	public void recv_close() {
		eventqueue.add(Events.RECV_CLOSE);
	}
	
	private Events getNextEvent() {

		Events event = null;

		try {

			event = eventqueue.poll(2, TimeUnit.SECONDS);

		} catch (InterruptedException ex) {
			System.out.println("Receiver - doProcess " + ex.getMessage());
			ex.printStackTrace();
		}

		return event;
	}
	
	public void doProcess () {
		
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

			System.out.println("Receiver[" + state + "]" + "(" + event + ")");
			
			switch (event) {
			case RECV_OPEN:
				state = States.OPEN;
				System.out.println("Receiver -> OPEN");
				break;
			default:
				break;
			}
		}
	}
	
	public void doOpen() {
		
		Events event = getNextEvent();

		if (event != null) {

			System.out.println("Receiver[" + state + "]" + "(" + event + ")");
			
			switch (event) {
			case RECV_DATA:
				dlv_data();
				break;
			case RECV_CLOSE:
				state = States.CLOSED;
				System.out.println("Receiver -> CLOSED");
				break;
			default:
				break;
			}
		}
	}
	
	// actions
	private int i = 1;
	
	public void dlv_data () {
		System.out.println("Receiver(DLV_DATA)[" + i + "]");
		i++;
	}
}
