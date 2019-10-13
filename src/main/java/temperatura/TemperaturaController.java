package temperatura;

import temperatura.model.Temperatura;
import javax.persistence.EntityManager;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import com.panamahitek.PanamaHitek_MultiMessage;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class TemperaturaController {
	static PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
	static PanamaHitek_MultiMessage multi = new PanamaHitek_MultiMessage(2, ino);
	static SerialPortEventListener listener = new SerialPortEventListener() {

		public void serialEvent(SerialPortEvent serialPortEvent) {
			try {
				EntityManager entity = Conexion.getEntityManagerFactory().createEntityManager();

				Temperatura temperatura = new Temperatura();

				if (multi.dataReceptionCompleted()) {
					System.out.println(multi.getMessage(0));
					temperatura.setTemp_temper(multi.getMessage(0));
					entity.getTransaction().begin();
					entity.persist(temperatura);
					entity.getTransaction().commit();
                    System.out.println(multi.getMessage(1));
					temperatura.setTemp_hum(multi.getMessage(1));;
					entity.getTransaction().begin();
					entity.persist(temperatura);
					entity.getTransaction().commit();
					multi.flushBuffer();
				}
			} catch (ArduinoException e) {
				e.printStackTrace();
			} catch (SerialPortException e) {
				e.printStackTrace();
			}
		}

	};

	public static void main(String[] args) {
		try {
			ino.arduinoRX("COM5", 9600, listener);
		} catch (ArduinoException e) {
			e.printStackTrace();
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}
}