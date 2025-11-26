package com.crapz.conversordemonedas.modelos;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {

    public BigDecimal convertirMonedas(String convertFrom, String convertTo, BigDecimal quantity) {
        // se usa LOWER_CASE_WITH_UNDERSCORES para mapear "conversion_result"
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        // COLOCAR LA API_KEY
        String API_KEY = "COLOCA_AQUI_TU_API_KEY";

        String direccion = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + convertFrom + "/" + convertTo + "/" + quantity;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("❌ ERROR HTTP: El servidor devolvió el código " + response.statusCode());
                System.out.println("Asegúrese de que su API_KEY y la URL sean correctas.");
                return BigDecimal.ZERO;
            }

            String json = response.body();

            ResultadoConversion resultado = gson.fromJson(json, ResultadoConversion.class);

            // Comprobación de éxito reportada por la API
            if (json.contains("\"result\":\"success\"")) {
                if (resultado != null && resultado.getConversionResult() != null) {
                    return resultado.getConversionResult();
                } else {
                    System.out.println("❌ Error interno: Conversión exitosa, pero el resultado es nulo.");
                    return BigDecimal.ZERO;
                }
            } else {
                // Si la API devuelve un error (ej. clave inválida, código no existe)
                System.out.println("❌ Error de la API: Revise su clave o los códigos de moneda. Respuesta de la API:");
                System.out.println(json);
                return BigDecimal.ZERO;
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error en la URI, verifique la dirección: " + e.getMessage());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error de conexión o interrupción: " + e.getMessage());
        }

        return BigDecimal.ZERO;
    }
}