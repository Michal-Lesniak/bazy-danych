import { DateAction } from "./dateAction";

export interface Repair {
    repairCode:string,
    status:boolean,
    customerCode: number,
    applianceCode: number,
    partCodes: number[],
    listOfDateCode: number[]
}
