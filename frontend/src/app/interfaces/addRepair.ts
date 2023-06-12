import { DateAction } from "./dateAction";

export interface addRepair{
    repairCode:string,
    status:boolean,
    customerCode: number,
    applianceCode: number,
    partCodes: number[],
    dateActionDtoList: DateAction[]
}