import { House } from './house';

export class User {
    id: number;
    username: string;
    password: string;
    houseId: number;
    token: string;


    constructor(username:string)
    {
        this.username = username;
    }
}