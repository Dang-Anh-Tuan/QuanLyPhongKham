tail.select("#select-medicine", {
  search: true,
});

tail.select("#select-method-use", {});

const tableList = document.querySelector(".table-list");
const inputNameMedicine = document.querySelector("#select-medicine");
const inputAmount = document.querySelector("#amount");
const inputMethodUse = document.querySelector("#select-method-use");
let listMedicine = [];
document
  .querySelector("#btn-add-medicine")
  .addEventListener("click", function () {
    let div = document.createElement("div");

    listMedicine.push({
        idMedicne : inputNameMedicine.options[inputNameMedicine.selectedIndex].value,
        amount : inputAmount.value,
        methodUse : inputMethodUse.options[inputMethodUse.selectedIndex].text
    });


    let nameMedicine =
      inputNameMedicine.options[inputNameMedicine.selectedIndex].text;
    let amount = inputAmount.value;
    let methodUse = inputMethodUse.options[inputMethodUse.selectedIndex].text;

    let html = `
    <div class="grid__col-lg-1 table-list__cell">${listMedicine.length}</div>
    <div class="grid__col-lg-3 table-list__cell">${nameMedicine}</div>
    <div class="grid__col-lg-2 table-list__cell">${amount}</div>
    <div class="grid__col-lg-3 table-list__cell">${methodUse}</div>
    `;
    div.innerHTML = html;
    div.className = "grid__row grid__row--spbw grid__row--item-center table-list__row"
    tableList.append(div);
  });
