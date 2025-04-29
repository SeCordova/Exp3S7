package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<String> ubicaciones = new ArrayList<String>();
    static ArrayList<Double> preciosBase = new ArrayList<Double>();
    static ArrayList<String> descuentosAplicados = new ArrayList<String>();
    static ArrayList<Double> preciosFinales = new ArrayList<Double>();


    static int totalEntradasVendidas = 0;
    static double totalIngresos = 0.0;
    static final String nombreTeatro = "Teatro Moro";
    static final int capacidadSala = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("==== Bienvenido al " + nombreTeatro + " ====");
            System.out.println("1. Venta de entradas");
            System.out.println("2. Visualizar resumen de ventas");
            System.out.println("3. Generar boleta");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    if (totalEntradasVendidas >= capacidadSala) {
                        System.out.println("Lo sentimos, ya no hay entradas disponibles.");
                        break;
                    }

                    System.out.println("Seleccione ubicacion:");
                    System.out.println("1. VIP ($15000)");
                    System.out.println("2. Platea ($10000)");
                    System.out.println("3. Balcon ($7000)");
                    System.out.print("Opcion: ");
                    int ubicacionElegida = scanner.nextInt();
                    scanner.nextLine();

                    String ubicacion = "";
                    double precioBase = 0.0;

                    switch (ubicacionElegida) {
                        case 1:
                            ubicacion = "VIP";
                            precioBase = 15000;
                            break;
                        case 2:
                            ubicacion = "Platea";
                            precioBase = 10000;
                            break;
                        case 3:
                            ubicacion = "Balcon";
                            precioBase = 7000;
                            break;
                        default:
                            System.out.println("Ubicacion no valida.");
                            break;
                    }

                    if (ubicacion.equals("")) {
                        break;
                    }

                    System.out.print("Es estudiante? (si/no): ");
                    String esEstudiante = scanner.nextLine();

                    double descuento = 0.0;
                    String tipoDescuento = "Sin descuento";

                    if (esEstudiante.equalsIgnoreCase("si")) {
                        descuento = 0.10;
                        tipoDescuento = "10% Estudiante";
                    } else {
                        System.out.print("Es persona de la tercera edad? (si/no): ");
                        String esTerceraEdad = scanner.nextLine();
                        if (esTerceraEdad.equalsIgnoreCase("si")) {
                            descuento = 0.15;
                            tipoDescuento = "15% Tercera Edad";
                        }
                    }

                    System.out.print("Cuantas entradas desea comprar?: ");
                    int cantidadEntradas = scanner.nextInt();
                    scanner.nextLine();

                    if (cantidadEntradas <= 0) {
                        System.out.println("Cantidad no valida.");
                        break;
                    }

                    if (totalEntradasVendidas + cantidadEntradas > capacidadSala) {
                        System.out.println("No hay suficientes entradas disponibles. Solo quedan " + (capacidadSala - totalEntradasVendidas));
                        break;
                    }

                    double precioFinalUnitario = precioBase - (precioBase * descuento);

                    for (int i = 0; i < cantidadEntradas; i++) {
                        ubicaciones.add(ubicacion);
                        preciosBase.add(precioBase);
                        descuentosAplicados.add(tipoDescuento);
                        preciosFinales.add(precioFinalUnitario);
                        totalEntradasVendidas++;
                        totalIngresos += precioFinalUnitario;
                    }

                    System.out.println("Entradas vendidas exitosamente.");
                    System.out.printf("Ubicacion: %s | Precio Unitario: $%.2f | Descuento: %s\n", ubicacion, precioFinalUnitario, tipoDescuento);
                    System.out.println("Cantidad de entradas compradas: " + cantidadEntradas);

                    break;

                case 2:
                    if (ubicaciones.isEmpty()) {
                        System.out.println("No hay ventas registradas aun.");
                    } else {
                        System.out.println("==== Resumen de Ventas ====");
                        for (int i = 0; i < ubicaciones.size(); i++) {
                            System.out.printf("Venta %d: Ubicacion: %s | Precio Final: $%.2f | Descuento: %s\n",
                                    (i + 1), ubicaciones.get(i), preciosFinales.get(i), descuentosAplicados.get(i));
                        }
                        System.out.println("Total de entradas vendidas: " + totalEntradasVendidas);
                        System.out.printf("Total de ingresos: $%.2f\n", totalIngresos);
                    }
                    break;

                case 3:
                    if (ubicaciones.isEmpty()) {
                        System.out.println("No hay ventas registradas para generar boleta.");
                    } else {
                        int ultimoIndice = ubicaciones.size() - 1;
                        System.out.println("--------------");
                        System.out.println(nombreTeatro);
                        System.out.println("Ubicacion = " + ubicaciones.get(ultimoIndice));
                        System.out.println("Costo base = $" + preciosBase.get(ultimoIndice));
                        System.out.println("Descuento aplicado = " + descuentosAplicados.get(ultimoIndice));
                        System.out.println("Costo total = $" + preciosFinales.get(ultimoIndice));
                        System.out.println("--------------");
                        System.out.println("Gracias por su visita al " + nombreTeatro);
                        System.out.println("--------------");
                    }
                    break;

                case 4:
                    System.out.println("¡Gracias por usar el sistema del Teatro Moro!");
                    break;

                default:
                    System.out.println("Opción no valida, intente nuevamente.");
                    break;
            }
            System.out.println();
        } while (opcion != 4);

        scanner.close();
    }
}
