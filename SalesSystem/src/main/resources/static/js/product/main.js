document.addEventListener("DOMContentLoaded", function() {
    showProducts();
});

const tableBody = document.getElementById('tableBody');
var productsList = [];

const showProducts = async () =>{
    tableBody.innerHTML = '';
    const response = await productService.findAll();
    const productsJson = response.json();

    productsJson.then(products => {
        products.forEach(product => {
                const {idProduct,name, price, stock} = product;
                productsList.push({idProduct,name, price, stock});
                const fila = document.createElement('tr');
                fila.innerHTML = `
                    <td>${idProduct}</td>
                    <td>${name}</td>
                    <td>$${price}</td>
                    <td>${stock}</td>
                    <td class="text-center" style="width:20px"><button class="btn btn-warning btn-edit rounded-pill" data-id="${idProduct}">Edit</button>
                    <td class="text-center" style="width:100px"><button class="btn btn-danger btn-delete rounded-pill" data-id="${idProduct}">Delete</button>
                `;
                tableBody.appendChild(fila);
            });
    });
}

document.addEventListener('click', function(event) {
    if (event.target.classList.contains('btn-edit')) {
        const idProduct = parseInt(event.target.getAttribute('data-id'));
        showUserInfo(idProduct);
    }
});

document.addEventListener('click', function(event) {
    if (event.target.classList.contains('btn-delete')) {
        const idProduct = parseInt(event.target.getAttribute('data-id'));
        deleteProduct(idProduct);
    }
});

function showUserInfo(idProduct) {
    const product = productsList.find(p => p.idProduct === idProduct);
    if (product) {
        document.getElementById('idProduct').value = product.idProduct;
        document.getElementById('name').value = product.name;
        document.getElementById('price').value = product.price;
        document.getElementById('stock').value = product.stock;
    }
}

function clearForm(){
    document.getElementById('idProduct').value = "";
    document.getElementById('name').value = "";
    document.getElementById('price').value = "";
    document.getElementById('stock').value = "";
}

document.getElementById('searchInput').addEventListener('input', function() {
    let searchValue = this.value.toLowerCase();
    let dataTable = document.getElementById('dataTable');
    let tableRows = dataTable.getElementsByTagName('tr');

    for (let i = 1; i < tableRows.length; i++) {
      let row = tableRows[i];
      let rowData = row.getElementsByTagName('td');
      let found = false;
      for (let j = 0; j < rowData.length; j++) {
        let cell = rowData[j];
        if (cell.textContent.toLowerCase().includes(searchValue)) {
          found = true;
          break;
        }
      }
      if (found) {
        row.style.display = '';
      } else {
        row.style.display = 'none';
      }
    }
});

const addProduct = async () => {

  const name = document.querySelector('#name').value;
  const price = document.querySelector('#price').value;
  const stock = document.querySelector('#stock').value;

  if (!name || !price || !stock) {
      alert('Los campos no pueden estar vacíos');
      return;
  }

  let data = {
    name: name,
    price: price,
    stock: stock
  };
  const response = await fetch("/api/products", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });
  if(response.status == 201){
    alert("Product saved");
    showProducts();
    clearForm();
  } else if(response.status == 400){
    alert("Error 404  " + response.error);
  } else{
        alert("Error nose  " + response.error);
  }
};

const deleteProduct = async (idProduct) => {

  alert("Delete id -> " + idProduct);
  const response = await productService.delete(idProduct);
  if(response.status == 204){
     alert("Eliminado");
     showProducts();
  } else {
        alert("Response => " + response.status)
  }
  //alert("Response => " + response.status)
  //showProducts();
};

const http = {
    fetch: async function(url, options = {}) {
        const response = await fetch(url, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            ...options,
        });

        return response;
    }
};

const productService = {
    findAll: async () => {
        return await http.fetch('/api/products');
    },
    add: async (data) => {
        return await http.fetch('/api/products', {
            method: 'POST',
            body: JSON.stringify(data)
        });
    },
    findById: async (id) => {
        return await http.fetch('/api/products/' + id);
    },
    update: async (id, data) => {
        return await http.fetch('/api/products/' + id, {
            method: 'PUT',
            body: JSON.stringify(data)
        });
    },
    delete: async (id) => {
        return await http.fetch('/api/products/' + id, {
            method: 'DELETE'
        });
    },
};