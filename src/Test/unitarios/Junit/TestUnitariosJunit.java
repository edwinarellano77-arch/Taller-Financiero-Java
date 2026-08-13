package Test.unitarios.Junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

	// ---- Pruebas de códigos consecutivos ----

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

	// ---- Pruebas del método depositar ----

	@Test
	public void testDepositarMontoPositivo() {
		Cuenta cuenta = banco.crearCuenta(new Cliente());
		cuenta.setSaldoActual(100);

		boolean resultado = banco.depositar(50, cuenta);

		assertTrue(resultado);                       // debe retornar true
		assertEquals(150, cuenta.getSaldoActual());  // 100 + 50
	}

	@Test
	public void testDepositarMontoCeroONegativo() {
		Cuenta cuenta = banco.crearCuenta(new Cliente());
		cuenta.setSaldoActual(100);

		boolean resultado = banco.depositar(-20, cuenta);

		assertFalse(resultado);                      // debe retornar false
		assertEquals(100, cuenta.getSaldoActual());  // el saldo NO cambia
	}
}