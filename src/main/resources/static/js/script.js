<script>
function addInputLine() {
alert( 'Hello, world!' );
var node = document.createElement("input");                 // Create an <input> node
document.getElementById("parentElement").appendChild(node);     // Append it to the parent
}
</script>
<script>
function addToList() {
alert( 'Hello, world!' );
  let food = document.getElementById("food").value;
  let amount = document.getElementById("amount").value;
  let unit = document.getElementById("unit").value;

  let li = document.createElement("li");
  li.textContent = food + ' ' + amount + ' ' + unit + '.';
  document.getElementById("foodlist").appendChild(li);
}
</script>