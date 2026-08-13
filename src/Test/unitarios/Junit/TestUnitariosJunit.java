package Test.unitarios.Junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestUnitariosJunit {

	private Banco banco;

	@BeforeEach
	public void setUp() {
		// Un Banco nuevo antes de cada prueba (ultimoCodigo = 1000)
		banco = new Banco();
	}

	@Test
	public void testPrimeraCuentaEmpiezaEn1000() {
		Cuenta c1 = banco.crearCuenta(new Cliente());
		assertEquals("1000", c1.getId());
	}

	@Test
	public void testCodigosConsecutivos() {
		Cuenta c1 = banco.crearCuenta(new Cliente());
		Cuenta c2 = banco.crearCuenta(new Cliente());
		Cuenta c3 = banco.crearCuenta(new Cliente());

		assertEquals("1000", c1.getId());
		assertEquals("1001", c2.getId());
		assertEquals("1002", c3.getId());
	}

	@Test
	public void testUltimoCodigoSeIncrementa() {
		banco.crearCuenta(new Cliente());
		banco.crearCuenta(new Cliente());
		// Tras crear 2 cuentas, el ultimoCodigo debe ir en 1002
		assertEquals(1002, banco.getUltimoCodigo());
	}
}