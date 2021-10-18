package cat.copernic.jmendezv

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.time.Duration
import java.util.stream.Stream
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals

internal class MainKtTest {


    // test inicio-final
    @Nested
    @DisplayName("InicioFinal")
    class General {

        companion object {

            @BeforeAll
            @JvmStatic
            fun init() {
                println("Inicio test's")
            }

            @AfterAll
            @JvmStatic
            fun end() {
                println("Final test's")
            }

            // parameter-method
            @JvmStatic
            fun provideParametersForPuntoMedio(): Stream<Arguments> {
                return Stream.of(
                    Arguments.of(Punto(3.2,1.1), Punto(2.1,3.3)),
                    Arguments.of(Punto(3.2,2.1), Punto(2.1,3.3))
                )
            }


        }
    }

    // test inicial antes de cada test
    @DisplayName("InicioCadaTest")
    @BeforeEach
    fun setUp() {
        println("Start test")
    }
    // test final antes de cada test
    @DisplayName("FinalCadaTest")
    @AfterEach
    fun tearDown() {
        println("Success test")
    }

    @DisplayName("ExceptionTest")
    @Test
    fun maxMinTest() {
        assertFailsWith(IllegalArgumentException::class) {
            Assertions.assertEquals(Pair(1, 7), maxMin(listOf()))
        }
    }


    @DisplayName("SimpleTest")
    @Test
    fun masCercanoTest(){
        masCercano(Punto(2.5, 7.5), Punto(2.0,3.2) )
    }


    @DisplayName("timeOutTest")
    @Test
    fun distanciaEntre2PuntosTest() {
        val result =
            org.junit.jupiter.api.assertTimeout(Duration.ofMillis(2350)) {
                distanciaEntre2Puntos(p1 = Punto(2.0,1.0), p2 = Punto(3.0,5.0))
            }
//        assert(true) { result }
        Assertions.assertEquals(4.1231056256176605498214098559741, result)
    }


    @DisplayName("parameterCSVsource")
    @ParameterizedTest
    @CsvSource("88.0,1.73,29.402920244578837","75.0,1.78,23.671253629592223204140891301603")
    fun imcTest(pes: Double, alcada: Double, resu: Double) {
        Assertions.assertEquals(resu, IMC(pes, alcada))
    }


    @DisplayName("simpleTest")
    @Test
    fun equSegundoGradoTest(){
        Assertions.assertNotEquals(Pair(5.2, 3.2), equSegundoGrado(3.2, 2.1, 1.1))
    }


    @DisplayName("SimpleTest")
    @Test
    fun calificacionTest() {
        assertEquals("Supera", calificacion(5.0))
    }

    @DisplayName("parameterMethod")
    @ParameterizedTest
    @MethodSource("provideParametersForPuntoMedio")
    fun puntoMedioTest(p1 : Punto, p2 : Punto) {
        assertNotEquals(Punto(3.2,1.1), puntoMedio(p1,p2))
    }

    @DisplayName("SimpleTest")
    @Test
    fun puntoMedioTestDos() {
        assertNotEquals(Punto(3.2,1.1), puntoMedio(Punto(3.2,1.1),Punto(4.2,2.1)))
    }

    @DisplayName("ParametersValueSource")
    @ParameterizedTest
    @ValueSource(doubles = [2.0, 9.0, 5.0])
    fun PendienteTest(a:Double) {
        Assertions.assertNotEquals(a, Pendiente(Punto(2.0, 1.0), Punto(-3.0, 2.0)))

    }

}