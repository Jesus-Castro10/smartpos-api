document.addEventListener("DOMContentLoaded", function () {
    console.log("NO FEAR");
    // console.log("desde el dom" + JSON.stringify(products));
});

document.addEventListener('click', function (event) {
    if (event.target.classList.contains('btn-edit')) {
        const idProduct = parseInt(event.target.getAttribute('data-id'));
        showUserInfo(idProduct);
    }
});

document.addEventListener('click', function (event) {
    if (event.target.classList.contains('btn-delete')) {
        const idProduct = parseInt(event.target.getAttribute('data-id'));
        deleteProduct(idProduct);
    }
});

function showUpdate() {
    document.getElementById("addForm").style.display = 'none';
    document.getElementById("updateForm").style.display = 'block';
}

function showAdd() {
    document.getElementById("addForm").style.display = 'block';
    document.getElementById("updateForm").style.display = 'none';

    document.getElementById('idProduct').value = "";
    document.getElementById('nameU').value = "";
    document.getElementById('priceU').value = "";
    document.getElementById('stockU').value = "";
}

function showInfo(id) {
    showUpdate();
    clearForm();
    const product = products.find(product => product.id === id);

    if (product) {
        document.getElementById('idProduct').value = product.id;
        document.getElementById('nameU').value = product.name;
        document.getElementById('priceU').value = product.price;
        document.getElementById('stockU').value = product.stock;
    } else {
        alert("No hay product");
    }
}

function clearForm() {
    document.getElementById('name').value = "";
    document.getElementById('price').value = "";
    document.getElementById('stock').value = "";
}

// document.getElementById('searchInput').addEventListener('input', function () {
//     let searchValue = this.value.toLowerCase();
//     let dataTable = document.getElementById('dataTable');
//     let tableRows = dataTable.getElementsByTagName('tr');

//     for (let i = 1; i < tableRows.length; i++) {
//         let row = tableRows[i];
//         let rowData = row.getElementsByTagName('td');
//         let found = false;
//         for (let j = 0; j < rowData.length; j++) {
//             let cell = rowData[j];
//             if (cell.textContent.toLowerCase().includes(searchValue)) {
//                 found = true;
//                 break;
//             }
//         }
//         if (found) {
//             row.style.display = '';
//         } else {
//             row.style.display = 'none';
//         }
//     }
// });

const addProduct = async () => {

    const name = document.querySelector('#name').value;
    const price = document.querySelector('#price').value;
    const stock = document.querySelector('#stock').value;

    alert("name " + name);
    if (name.value === '' || price.value === '' || stock.value === '') {
        alert('Los campos no pueden estar vacíos');
        return false;
    }
    return true;
};