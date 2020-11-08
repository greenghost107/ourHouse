import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Grocery } from '@app/_models';
import { GroceryListService } from '@app/_services';

@Component({
  selector: 'app-grocerylist',
  templateUrl: './grocerylist.component.html',
  styleUrls: ['./grocerylist.component.scss']
})
export class GrocerylistComponent implements OnInit {
  // @Input() id;
  @Input() currentGroceryList;
  @Output() onSaveClick = new EventEmitter();
  @Output() onDeleteClick = new EventEmitter();
  groceryList: Grocery[];
  toSave: Grocery[] = [];
  marked:Grocery[] = [];
  changedMarking: Grocery[] = [];
  
  finishedLoading:boolean = false;
  canComplete:boolean = false;
  displayExpense:boolean = false;
  constructor(private groceryListService: GroceryListService) { }

  ngOnInit(): void {
    if (this.currentGroceryList) {
      this.groceryListService.getGrocerysForId(this.currentGroceryList.id).subscribe((data)=>{
            this.groceryList = data;
            this.handleMarked();
            this.finishedLoading = true;
        });
    }
  }

  handleMarked(){
    for (let index = 0; index < this.groceryList.length; index++) {
      const element = this.groceryList[index];
      if(element.marked)
      {
        this.marked.push(element);
      }
    }
  }

  addGrocery(groceryNameInput,quantityInput){
    this.groceryList.push({name: groceryNameInput.value, quantity: Number(quantityInput.value), marked : false});
    this.toSave.push({name: groceryNameInput.value, quantity: Number(quantityInput.value), marked : false});
    groceryNameInput.value='';
    quantityInput.value='';
  }

  changeMarking(grocery: Grocery)
  {
    // console.log({name: grocery.name, quantity: Number(grocery.quantity), marked : grocery.marked});
    // console.log({name: grocery.name, quantity: Number(grocery.quantity), marked : !grocery.marked});
    // this.toMark.push({name: grocery.name, quantity: Number(grocery.quantity), marked : !grocery.marked});

    //if change from false to true
      //add to marked array
      //find if in changeMarking array
        // if exists just remove it
        //if not add it with changed sign


    //else change from true to false
      //find in marked array and remove it
      //find if in changeMarking array
        // if exists just remove it
        //if not add it with changed sign
        
    if (grocery.marked)
    {
      grocery.marked = false;
      
    }
    else{
      grocery.marked = true;
      
    }
    this.handleChangeMarking(grocery);
    if(this.marked.length==this.groceryList.length)
    {
      this.canComplete = true;
    }
    else{
      this.canComplete = false;
    }
    
  }

  //find if in changeMarking array
        // if exists just remove it
        //if not add it with changed sign
  handleChangeMarking(grocery:Grocery)
  {
    const found = this.changedMarking.find((groc)=>groc.name == grocery.name);
    if (found)
    {
      
      this.changedMarking = this.changedMarking.filter((g)=>g.name != grocery.name);
    }
    else{
      this.changedMarking.push({name: grocery.name, quantity: Number(grocery.quantity), marked : grocery.marked});
    }
  }
  // saveGroceryList()
  // {
  //   if(this.toSave.length>0)
  //   {
  //   this.groceryListService.saveGroceryList(this.toSave,this.id).subscribe(()=>{});
  //   console.log("finished saving");
  //   }
  //   else{
  //     console.log("nothing to save");
  //   }
  // }

  // deleteGroceryList(){
  //   this.groceryListService.deleteGroceryList(this.id).subscribe(()=>{
  //   });
  // }
  saveGroceryList()
  {
    if(this.toSave.length>0)
    {
    this.groceryListService.saveGroceryList(this.toSave,this.currentGroceryList.id).subscribe(()=>{
      console.log("finished saving");
      this.onSaveClick.emit();
    });
    
    }
    else{
      console.log("nothing to save");
    }
    //TODO fix update
    if(this.changedMarking.length>0)
    {
      // console.log(this.toMark);
      this.groceryListService.updateGroceryList(this.changedMarking,this.currentGroceryList.id).subscribe(()=>{
        console.log("finished updating");
      this.onSaveClick.emit();
      }
      );
    }
    else{
      console.log("nothing to update");
    }
  }
  displayExpenseDialog()
  {
    this.displayExpense = true;
  }


  deleteGroceryList(price:number){
    console.log(price);
    this.groceryListService.deleteGroceryList(this.currentGroceryList.id,+price).subscribe(()=>{
      this.onDeleteClick.emit();
    });
  }

}
