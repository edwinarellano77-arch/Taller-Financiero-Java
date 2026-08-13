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
		banco = new Banco();
	}

	// ---- Códigos consecutivos ----

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
		assertEquals(1002, banco.getUltimoCodigo());
	}

	// ---- Depositar ----

	@Test
	public void testDepositarMontoPositivo() {
		Cuenta cuenta = banco.crearCuenta(new Cliente());
		cuenta.setSaldoActual(100);

		boolean resultado = banco.depositar(50, cuenta);

		assertTrue(resultado);
		assertEquals(150, cuenta.getSaldoActual());
	}

	@Test
	public void testDepositarMontoCeroONegativo() {
		Cuenta cuenta = banco.crearCuenta(new Cliente());
		cuenta.setSaldoActual(100);

		boolean resultado = banco.depositar(-20, cuenta);

		assertFalse(resultado);
		assertEquals(100, cuenta.getSaldoActual());
	}

	// ---- Retirar ----

	@Test
	public void testRetirarMontoValido() {
		Cuenta cuenta = banco.crearCuenta(new Cliente());
		cuenta.setSaldoActual(100);

		boolean resultado = banco.retirar(40, cuenta);

		assertTrue(resultado);
		assertEquals(60, cuenta.getSaldoActual());
	}

	@Test
	public void testRetirarMontoMayorAlSaldo() {
		Cuenta cuenta = banco.crearCuenta(new Cliente());
		cuenta.setSaldoActual(100);

		boolean resultado = banco.retirar(150, cuenta);

		assertFalse(resultado);
		assertEquals(100, cuenta.getSaldoActual());
	}

	@Test
	public void testRetirarMontoCeroONegativo() {
		Cuenta cuenta = banco.crearCuenta(new Cliente());
		cuenta.setSaldoActual(100);

		boolean resultado = banco.retirar(-30, cuenta);

		assertFalse(resultado);
		assertEquals(100, cuenta.getSaldoActual());
	}

	// ---- Transferir ----

	@Test
	public void testTransferirExitosa() {
		Cuenta origen = banco.crearCuenta(new Cliente());
		Cuenta destino = banco.crearCuenta(new Cliente());
		origen.setSaldoActual(100);
		destino.setSaldoActual(50);

		boolean resultado = banco.transferir(origen, destino, 30);

		assertTrue(resultado);                        // transferencia permitida
		assertEquals(70, origen.getSaldoActual());    // 100 - 30
		assertEquals(80, destino.getSaldoActual());   // 50 + 30
	}

	@Test
	public void testTransferirFondosInsuficientes() {
		Cuenta origen = banco.crearCuenta(new Cliente());
		Cuenta destino = banco.crearCuenta(new Cliente());
		origen.setSaldoActual(100);
		destino.setSaldoActual(50);

		boolean resultado = banco.transferir(origen, destino, 150);

		assertFalse(resultado);                       // retiro falla -> no transfiere
		assertEquals(100, origen.getSaldoActual());   // origen intacto
		assertEquals(50, destino.getSaldoActual());   // destino intacto
	}

	@Test
	public void testTransferirMontoInvalido() {
		Cuenta origen = banco.crearCuenta(new Cliente());
		Cuenta destino = banco.crearCuenta(new Cliente());
		origen.setSaldoActual(100);
		destino.setSaldoActual(50);

		boolean resultado = banco.transferir(origen, destino, -10);

		assertFalse(resultado);                       // monto invalido
		assertEquals(100, origen.getSaldoActual());   // nada se mueve
		assertEquals(50, destino.getSaldoActual());
	}
}