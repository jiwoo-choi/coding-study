import DateBuilder from "./DateManager";
export default class $ {
    static get datebuilder() : DateBuilder {
        return DateBuilder.getInstance();
    }
}
