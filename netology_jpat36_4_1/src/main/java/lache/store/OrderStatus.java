package lache.store;

public enum OrderStatus {
    CANCELLED ("отменен"),
    CREATED ("создан"),
    GATHERING ("собирается"),
    READY_FOR_SHIPMENT ("готов к доставку"),
    ON_THE_WAY ("доставляется"),
    RECEIVED ("вручен получателю"),
    COMPLETED ("завершен");

    private String descRu;
    OrderStatus(String descRu){
        this.descRu = descRu;
    }
    public String getDescription(){ return descRu;}
    public OrderStatus next(OrderStatus currentStatus) {
        switch (currentStatus) {
            case CREATED:
                return GATHERING;
            case GATHERING:
                return READY_FOR_SHIPMENT;
            case READY_FOR_SHIPMENT:
                return ON_THE_WAY;
            case RECEIVED:
                return COMPLETED;
            default:
                System.out.println("Невозможно изменение статуса " + currentStatus);
                return currentStatus;
        }
    }

    public OrderStatus cancel(OrderStatus currentStatus) {
        switch (currentStatus) {
            case CREATED:
            case GATHERING:
            case READY_FOR_SHIPMENT:
                return CANCELLED;
            default:
                System.out.println("Невозможно отменить заказ из статуса " + currentStatus);
                return currentStatus;
        }
    }
}
