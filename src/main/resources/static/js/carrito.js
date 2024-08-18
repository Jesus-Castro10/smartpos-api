const cart = {};
const cartList = document.getElementById('table-cart');
const total = document.getElementById('total');
const iva = document.getElementById('iva');
function addToCart(name,price) {
        const e = 'amount_' + name;
        const amountElement = document.getElementById(e);
        const amount = parseInt(amountElement.value);

        if (amount < 1) {
            alert('The amount must be greater than 0');
            return;
        }
        if(cart[name]){
            cart[name].amount += amount;
        } else {
            cart[name] = {
                price,
                amount
            };
        }
        showCart();
}

function showCart() {
        cartList.innerHTML = '';
        let sTotal = 0;
        var c = 0;
        for (const name in cart) {
            c++;
            const { price, amount } = cart[name];
            const fila = document.createElement('tr');
            //const subtotal = Math.round(price * amount);
            const n = price * amount;
            const subtotal = Number(n.toFixed(2));
            fila.innerHTML = `
                <td>${c}</td>
                <td>${name}</td>
                <td>${amount}</td>
                <td>$${price}</td>
                <td>$${subtotal}</td>
            `;
            cartList.appendChild(fila);

            sTotal += subtotal;
        }
       const cIva = sTotal * 0.19;
       sTotal += cIva;
       iva.textContent = "Iva : $" + Number(cIva.toFixed(2));
       total.textContent = "Total : $" + Number(sTotal.toFixed(2));
}