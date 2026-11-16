/*const API_URL = "http://localhost:8080/api/carrito";

const tbody = document.getElementById("productos");
const listaResumen = document.getElementById("resumen");
const precioTotal = document.getElementById("precioTotal"); 

let product;
const filas = new Map(); // sku -> { input, celdaTotal }

// Utilidades 
function formatear(importe) {
    return new Intl.NumberFormat("es-ES", { maximumFractionDigits: 2 }).format(importe) + product.moneda;
}

function crear(etiqueta, clase, texto) {
    const el = document.createElement(etiqueta);
    if (clase) el.className = clase;
    if (texto !== undefined) el.textContent = texto;
    return el;
}



function renderProducts(productos) {
    tbody.innerHTML = "";
    filas.clear();

    productos.forEach(producto => {
        const tr = crear("tr");

        // Producto + referencia
        const tdProducto = crear("td", "col-producto");
        tdProducto.append(
            crear("div", "producto-titulo", producto.title),
            crear("div", "producto-ref", `Ref: ${producto.sku}`)
        );

        // Cantidad: - [input] +
        const tdCantidad = crear("td", "col-cantidad");
        const btnMenos = crear("button", "btn-cantidad", "-");
        const btnMas = crear("button", "btn-cantidad", "+");
        const input = crear("input", "input-cantidad");
        input.type = "number";
        input.min = "0";
        input.step = "1";
        input.value = producto.quantity;
        tdCantidad.append(btnMenos, input, btnMas);

        // Precio unitario y total de la línea
        const tdUnidad = crear("td", "", formatear(producto.price));
        const tdTotal = crear("td", "", formatear(producto.total));

        tr.append(tdProducto, tdCantidad, tdUnidad, tdTotal);
        tbody.appendChild(tr);
        filas.set(producto.sku, { input, celdaTotal: tdTotal });

        // Eventos
        btnMenos.addEventListener("click", () => {
            cambiarUnidades(producto.sku, Number(input.value) - 1);
        });
        btnMas.addEventListener("click", () => {
            cambiarUnidades(producto.sku, Number(input.value) + 1);
        });
        input.addEventListener("input", () => {
            cambiarUnidades(producto.sku, input.value, false);
        });
        input.addEventListener("change", () => {
            cambiarUnidades(producto.sku, input.value);
        });
    });

    renderTotal();
}



function cambiarUnidades(sku, unidades, corregirInput = true) {
    const unidadesFinales = carrito.actualizarUnidades(sku, unidades);
    const fila = filas.get(sku);

    if (corregirInput) {
        fila.input.value = unidadesFinales;
    }
    fila.celdaTotal.textContent = formatear(carrito.obtenerInformacionProducto(sku).total);

    renderTotal();
}

function renderTotal() {
    const datos = carrito.obtenerCarrito();

    listaResumen.innerHTML = "";
    datos.products.forEach(p => {
        const li = crear("li");
        li.append(crear("span", "", p.title), crear("span", "", formatear(p.total)));
        listaResumen.appendChild(li);
    });

    precioTotal.textContent = formatear(datos.total);
}




fetch(API_URL)
    .then(response => {
        if (!response.ok) throw new Error(`HTTP ${response.status}`);
        return response.json();
    })
    .then(data => {
        product = new Product(data.products, data.currency);
        renderProducts(product.obtenerProductos());
    })
    .catch(error => {
        console.error("Error al cargar los productos:", error);
        tbody.innerHTML = "";
        const tr = crear("tr");
        const td = crear("td", "error", `No se pudieron cargar los productos (${error.message}). Prueba a abrir ${API_URL} en el navegador.`);
        td.colSpan = 4;
        tr.appendChild(td);
        tbody.appendChild(tr);
    });
*/



const API_URL = "http://localhost:8080/api/products";

fetch(API_URL)
    .then(response => {
        if (!response.ok) throw new Error(`HTTP ${response.status}`);
        return response.json();
    })
    .catch(error => {
        console.error("Error al cargar los productos:", error);
        const productContainer = document.getElementById("product-container");
    });
