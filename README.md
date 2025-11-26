# ChallengeConversorDeMonedas
# 💰 Conversor de Monedas

Este proyecto es una aplicación de consola en Java diseñada para realizar conversiones de moneda en tiempo real utilizando una API externa.

## 🚀 Funcionalidad

La aplicación permite a los usuarios seleccionar entre las siguientes conversiones bidireccionales de forma interactiva:

1.  **Dólar (USD) 🔄 Peso argentino (ARS)**
2.  **Dólar (USD) 🔄 Real brasileño (BRL)**
3.  **Dólar (USD) 🔄 Peso colombiano (COP)**

El programa se ejecuta en un ciclo continuo mostrando un menú hasta que el usuario elige la opción de "Salir".

---

## 🏗️ Estructura del Proyecto

El código está organizado en tres paquetes principales:

### 📦 `com.crapz.conversordemonedas.principal`

| Clase | Descripción |
| :--- | :--- |
| `Main` | **Punto de entrada** principal de la aplicación. Crea una instancia de `Interfaz` e inicia el ciclo del menú. |

### 📦 `com.crapz.conversordemonedas.interfaz`

| Clase | Descripción |
| :--- | :--- |
| `Interfaz` | Gestiona toda la **interacción con el usuario**. Maneja el bucle del menú y la **validación** de la entrada numérica (entero para la opción y `BigDecimal` para la cantidad). |

### 📦 `com.crapz.conversordemonedas.modelos`

| Clase | Descripción |
| :--- | :--- |
| `Conversor` | Contiene la lógica central para interactuar con la **API externa**. Construye la URL, realiza la solicitud HTTP y maneja la respuesta JSON. |
| `ResultadoConversion` | Clase **modelo** utilizada por Gson para mapear el resultado de la conversión (campo `conversion_result`). |

---

## 🌐 Tecnología y Dependencias

El proyecto utiliza la librería **Gson** para la manipulación y el *parsing* de datos JSON que se reciben de la API.

-   **API Externa:** ExchangeRate-API (para obtener las tasas de conversión en tiempo real).
-   **Clase para HTTP:** `java.net.http.HttpClient` (Clase de Java estándar para manejar peticiones web).
-   **JSON:** **Gson** (Gestiona la deserialización del JSON).

### Deserialización con Gson

Se utiliza `GsonBuilder` con la política `FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES` para mapear correctamente el campo `conversion_result` del JSON (que usa guiones bajos) al campo correspondiente en la clase `ResultadoConversion`.

---

## ⚠️ Manejo de Errores

La clase `Conversor` incluye una gestión robusta de errores para asegurar la fiabilidad:

1.  **Errores HTTP:** Verifica el código de estado de la respuesta. Si no es **200 (OK)**, informa del error.
2.  **Errores de la API:** Comprueba el campo `"result"` dentro del JSON para asegurar que la conversión fue exitosa.
3.  **Excepciones de Conexión:** Maneja `IOException` e `InterruptedException` para problemas de red.
4.  **Validación de Entrada:** La clase `Interfaz` valida que las entradas del usuario sean números válidos para evitar fallos.

En caso de fallo, el método `convertirMonedas` devuelve `BigDecimal.ZERO` y se imprime un mensaje de error detallado en la consola.
