var groceryInput = document.getElementById('grocery');
var quantityInput = document.getElementById('quantity');
var addGroceryButton = document.getElementById('addGroceryButton');
var groceryListTodo = document.getElementById('groceryListTodo');
var completedTask = document.getElementById('completedTasks');
var saveGroceryList = document.getElementById('saveGroceryList');
var savedGroceryList = []
var addGrocery = function () {
    var grocery = groceryInput.value;
    var quantity = quantityInput.value;
    savedGroceryList.push({"name":grocery,"quantity":quantity});
    var li = document.createElement('li');
    li.innerHTML = "<input type='checkbox'> " +
                   "<label>" + grocery + "</label> " +
                   "<label>" + quantity + "</label>" +
                   " <button class='delete'>Delete</button>";
    groceryListTodo.appendChild(li);
}
var saveGroceryList = () => {
console.log(savedGroceryList);

var username = "Alexandra";
var password ="12345678a";
console.log("authorization : Basic "
                    + btoa(username + ":" + password));
var xhr = new XMLHttpRequest();
xhr.open("POST", "http://localhost:8080/user/index2", false, username,password);
xhr.setRequestHeader('Content-Type', 'application/json');
xhr.setRequestHeader("Accept", "application/json");
xhr.send(JSON.stringify({
    value: savedGroceryList
}));
//xhr.send();
//return JSON.stringify({
//           value: savedGroceryList
//       });
}

addGroceryButton.onclick = addGrocery;
