export class House {
    id: number;
    houseName: string;
    housePassword: string;
    
constructor(housename:string,housePassword: string,id?:number)
{
    this.houseName = housename;
    this.housePassword = housePassword;
    if(id){
        this.id = id;
    }
}

}

