function validation() {
  const code = document.getElementById('code');
  const productName = document.getElementById('name');
  const price = document.getElementById('price');
  const amount = document.getElementById('amount');

  if (productName.value === "") {
    alert("Select a product");
    code.focus();
    return false;
  }
  if (amount.value === "") {
    alert("Enter the amount");
    amount.focus();
    return false;
  }
  return true;
}

function validateSale(){
    const customer = document.getElementById('customerId');
    const customerField = document.getElementById('idCustomer');

    const session = request.getSession();
    const detailsList = session.getAttribute("detailsList");

    if(customer.value === "0000"){
        alert("Select a customer");
        customerField.focus();
        //return false
    }
    return false;
}

function validarFormulario() {
    const customer = document.getElementById('customerId');
    const customerField = document.getElementById('idCustomer');
    c = true;
    fetch('/getSaleDetails')
        .then(response => response.json())
        .then(saleDetailsList => {
            if (saleDetailsList && saleDetailsList.length < 1) {
                alert("You must select at least 1 product");
                c = false;
                return false;
            }
        })
        .catch(error => {
            console.error('Error al obtener la lista de clientes desde la sesión:', error);
            // Puedes manejar el error de alguna manera y decidir si permitir o no el envío del formulario
            c = false;
            return false;
        });

    if(customer.value === "0000"){
            alert("Select a customer");
            customerField.focus();
            c = false;
            return false
    }
    if(c === true){
        alert("Venta guardada");
    }
    return true;
}
