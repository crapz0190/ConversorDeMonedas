package com.crapz.conversordemonedas.interfaz;

import com.crapz.conversordemonedas.modelos.Conversor;

import java.math.BigDecimal;
import java.util.Scanner;

public class Interfaz {

    private static final String USD = "USD";
    private static final String ARS = "ARS";
    private static final String BRL = "BRL";
    private static final String COP = "COP";

    public void mostrarMenu() {
        System.out.println("\n========== CONVERSOR DE MONEDAS ==========");
        System.out.println("1) Dólar (USD) =>> Peso argentino (ARS)");
        System.out.println("2) Peso argentino (ARS) =>> Dólar (USD)");
        System.out.println("3) Dólar (USD) =>> Real brasileño (BRL)");
        System.out.println("4) Real brasileño (BRL) =>> Dólar (USD)");
        System.out.println("5) Dólar (USD) =>> Peso colombiano (COP)");
        System.out.println("6) Peso colombiano (COP) =>> Dólar (USD)");
        System.out.println("7) Salir");
        System.out.println("==========================================");
    }

    public void menuOpciones() {
        Scanner scanner = new Scanner(System.in);
        boolean ejecutarMenu = true;
        Conversor conversor = new Conversor();

        while (ejecutarMenu) {
            mostrarMenu();

            System.out.print("Elija una opción válida: ");
            int opcion;

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("❌ Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine();
                continue;
            }

            String from, to;

            switch (opcion) {
                case 1:
                    from = USD; to = ARS;
                    valorAConvertir(scanner, conversor, from, to);
                    break;
                case 2:
                    from = ARS; to = USD;
                    valorAConvertir(scanner, conversor, from, to);
                    break;
                case 3:
                    from = USD; to = BRL;
                    valorAConvertir(scanner, conversor, from, to);
                    break;
                case 4:
                    from = BRL; to = USD;
                    valorAConvertir(scanner, conversor, from, to);
                    break;
                case 5:
                    from = USD; to = COP;
                    valorAConvertir(scanner, conversor, from, to);
                    break;
                case 6:
                    from = COP; to = USD;
                    valorAConvertir(scanner, conversor, from, to);
                    break;
                case 7:
                    ejecutarMenu = false;
                    System.out.println("👋 Saliendo del programa. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("⚠️ Opción no reconocida. Intente de nuevo.");
            }

            if (ejecutarMenu) {
                System.out.println("\n--- Presione ENTER para continuar ---");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private void valorAConvertir(Scanner scanner, Conversor conversor, String convertFrom, String convertTo) {
        BigDecimal quantity;

        System.out.printf("Ingrese el valor en %s que deseas convertir a %s: ", convertFrom, convertTo);

        if (scanner.hasNextBigDecimal()) {
            quantity = scanner.nextBigDecimal();
            scanner.nextLine();

            System.out.printf("Realizando conversión de %s %s a %s...", quantity, convertFrom, convertTo);

            BigDecimal result = conversor.convertirMonedas(convertFrom, convertTo, quantity);

            if (result.compareTo(BigDecimal.ZERO) != 0) {
                System.out.print("\n=======================================================");
                System.out.printf("\n✅ El valor de %s %s equivale a %s %s.\n", quantity, convertFrom, result, convertTo);
                System.out.print("=======================================================");
            } else {
                System.out.println("\nNo se pudo mostrar el resultado. Revise los errores anteriores.");
            }

        } else {
            System.out.println("❌ Entrada no válida. Por favor, ingrese un número decimal (ej. 10,5).");
            scanner.nextLine();
        }
    }
}