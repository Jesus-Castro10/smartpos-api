// Función para filtrar una lista
function filterList(searchValue, listId) {
    let listItems = document.querySelectorAll('#list li')
    listItems.forEach(function(item) {
        let text = item.textContent.toLowerCase();
        if (text.includes(searchValue)) {
            item.style.display = 'block';
        } else {
            item.style.display = 'none';
        }
    });
}