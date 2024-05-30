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

const saleService = {
    add: async (data) => {
        return await http.fetch('/saveSale', {
            method: 'POST',
            body: JSON.stringify(data)
        });
    }
};

async function addSale() {
    alert("AddSale");
    let data = {
        product : {
            idProduct : 1
        },
        amount: 2,
        subtotal : 10
    };

    const bookResponse = await saleService.add(data);
    if (bookResponse.status == 200) {
        alert("201");
        alert(bookResponse)
    } else {
        alert("no 201")
    }
}

function addSales(){
    addSale();
}