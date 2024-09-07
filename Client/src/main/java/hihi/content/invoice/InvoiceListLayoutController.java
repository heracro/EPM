package hihi.content.invoice;

import hihi.application.config.GuiConfig;
import hihi.content.common.contentList.ContentListLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
public class InvoiceListLayoutController
        extends ContentListLayoutController<Invoice> {

    @FXML
    private TableColumn<Invoice, LocalDate> invoiceDateColumn;
    @FXML
    private TableColumn<Invoice, String> invoiceNumberColumn;
    @FXML
    private TableColumn<Invoice, String> shopNameColumn;
    @FXML
    private TableColumn<Invoice, Float> totalAmountColumn;
    @FXML
    private TableColumn<Invoice, String> statusColumn;
    @FXML
    private TableColumn<Invoice, LocalDate> orderDateColumn;
    @FXML
    private TableColumn<Invoice, LocalDate> paymentDateColumn;
    @FXML
    private TableColumn<Invoice, LocalDate> paymentDueDateColumn;
    @FXML
    private TableColumn<Invoice, LocalDate> deliveryDateColumn;
    @FXML
    private TableColumn<Invoice, LocalDate> plannedDateColumn;
    @FXML
    private TableColumn<Invoice, String> parcelTrackingNumberColumn;

    public InvoiceListLayoutController() {
        super("Invoice");
    }

    @Override
    protected List<Double> getColumnWidthsMultipliers() {
        return Stream.concat(super.getColumnWidthsMultipliers().stream(),
                Arrays.stream(GuiConfig.INVOICE_LIST_COL_WIDTH).boxed()).toList();
    }

    @FXML
    @Override
    public void initialize() {
        log.info("\033[93m initialize() \033[0m");
        super.initialize();
        setupColumns();
        log.info("\033[93m END OF initialize() \033[0m");
    }

}
