package ibfb.workbook.core;

import ibfb.domain.core.Factor;
import ibfb.domain.core.GermplasmList;
import ibfb.domain.core.ListOfEntries;
import ibfb.domain.core.Workbook;
import ibfb.workbook.api.GermplasmListReader;
import ibfb.workbook.utils.ExcelUtils;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.ListdataPK;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.cimmyt.cril.ibwb.domain.util.WheatData;

/**
 *
 * @author TMSANCHEZ
 */
public class GermplasmListReaderImpl implements GermplasmListReader {

    private int rowHeader;
    private static Logger log = Logger.getLogger(GermplasmListReaderImpl.class);
    /**
     * Sheet number for Germplasm list
     */
    private Integer sheetNumber = 0;
    /**
     * Sheet name for germplasm lists
     */
    private String sheetName;
    /**
     *
     */
    private List<Factor> entryFactors;
    /**
     *
     */
    private Map<String, Integer> columnMap = new HashMap<String, Integer>();
    private Integer gidHeaderIndex;
    private Integer entryCodeHeaderIndex;
    private Integer designationHeaderIndex;
    private Integer crossHeaderIndex;
    private Integer sourceHeaderIndex;
    private Integer entryIdHeaderIndex;

    @Override
    public boolean isValidTemplate(String fileName) throws Exception {
        boolean valid = false;

        log.info("Validating Excel file read BEGIN");
        log.info("Opening file...");
        InputStream inputStream = new FileInputStream(fileName);
        org.apache.poi.ss.usermodel.Workbook excelBook = WorkbookFactory.create(inputStream);
        log.info("Number of sheets in book" + excelBook.getNumberOfSheets());

        // assume values are in B colum (index 1)
        int labelColNumber = 0;
        int listRowIndex = 0;

        // get sheet where is located list data
        Sheet sheet = excelBook.getSheetAt(sheetNumber);

        Cell cellDataGid = null;
        Cell cellDataEntryCode = null;
        Cell cellDataDesignation = null;
        Row rowData = null;

        rowHeader = getRowHeaderIndex(excelBook);

        if (rowHeader > -1) {
            rowData = sheet.getRow(rowHeader);
            // check if has entry factors
            int gidIndex = COLUMN_GID;
            int entryCodeIndex = COLUMN_ENTRY_CODE;
            int designationIndex = COLUMN_DESIGNATION;
            String gidTextHeader = HEADER_GID;
            String entryCodeTextHeader = HEADER_ENTRY_CODE;
            String designationTextHeader = HEADER_DESIGNATION;
            if (hasEntryFactors()) {
                gidIndex = gidHeaderIndex;
                entryCodeIndex = entryCodeHeaderIndex;
                designationIndex = designationHeaderIndex;
                // if any of following factore is null then template does not containt minimal factors required
                if (gidHeaderIndex == null || entryCodeHeaderIndex == null || designationHeaderIndex == null) {
                    return false;
                }
                gidTextHeader = entryFactors.get(gidIndex).getFactorName();
                entryCodeTextHeader = entryFactors.get(entryCodeIndex).getFactorName();
                designationTextHeader = entryFactors.get(designationIndex).getFactorName();


            }

            cellDataGid = rowData.getCell(gidIndex);
            cellDataEntryCode = rowData.getCell(entryCodeIndex);
            cellDataDesignation = rowData.getCell(designationIndex);
            if (ExcelUtils.getStringValueFromCell(cellDataGid).toUpperCase().startsWith(gidTextHeader)
                    && ExcelUtils.getStringValueFromCell(cellDataEntryCode).toUpperCase().startsWith(entryCodeTextHeader)
                    && ExcelUtils.getStringValueFromCell(cellDataDesignation).toUpperCase().startsWith(designationTextHeader)) {
                valid = true;
            }
        }

        return valid;
    }

    @Override
    public boolean isValidCrossScript(String fileName) throws Exception {
        boolean valid = false;

        log.info("Validating Cross Excel file read BEGIN");
        log.info("Opening file...");
        InputStream inputStream = new FileInputStream(fileName);
        org.apache.poi.ss.usermodel.Workbook excelBook = WorkbookFactory.create(inputStream);

        int labelColNumber = 0;
        int listRowIndex = 0;

        Sheet sheet = excelBook.getSheetAt(sheetNumber);

        Cell cellDataGid = null;
        Cell cellDataEntryCode = null;
        Cell cellDataDesignation = null;
        Row rowData = null;


        //PENDIENTE 

        valid = true;

        return valid;
    }

    @Override
    public GermplasmList getGermPlasmList(String fileName) throws Exception {
        GermplasmList germplasmList = new GermplasmList();

        log.info("Excel file read BEGIN");
        log.info("Opening file...");
        InputStream inputStream = new FileInputStream(fileName);
        org.apache.poi.ss.usermodel.Workbook excelBook = WorkbookFactory.create(inputStream);
        log.info("Number of sheets in book" + excelBook.getNumberOfSheets());

        fillGermplasmList(germplasmList, excelBook);

        log.info("Excel file read END");
        return germplasmList;
    }

    private void fillGermplasmList(GermplasmList germplasmList, org.apache.poi.ss.usermodel.Workbook excelBook) {
        if (rowHeader > 4) {
            fillHeader(germplasmList, excelBook);
        }
        if (hasEntryFactors()) {
            fillEntryListUsingFactors(germplasmList, excelBook);
        } else {
            fillEntryList(germplasmList, excelBook);
        }
    }

    private void fillHeader(GermplasmList germplasmList, org.apache.poi.ss.usermodel.Workbook excelBook) {
        // assume values are in B colum (index 1)
        int dataColNumber = 1;
        int listRowIndex = 0;

        // get sheet where is located list data
        Sheet sheet = excelBook.getSheetAt(sheetNumber);

        Cell cellData = null;
        Row rowData = null;

        rowData = sheet.getRow(listRowIndex);
        cellData = rowData.getCell(dataColNumber);
        germplasmList.setId(ExcelUtils.getIntValueFromCell(cellData));

        listRowIndex++;
        rowData = sheet.getRow(listRowIndex);
        cellData = rowData.getCell(dataColNumber);
        germplasmList.setName(ExcelUtils.getStringValueFromCell(cellData));

        listRowIndex++;
        rowData = sheet.getRow(listRowIndex);
        cellData = rowData.getCell(dataColNumber);
        germplasmList.setDate(ExcelUtils.getDateValueFromCell(cellData));

        listRowIndex++;
        rowData = sheet.getRow(listRowIndex);
        cellData = rowData.getCell(dataColNumber);
        germplasmList.setType(ExcelUtils.getStringValueFromCell(cellData));

        listRowIndex++;
        rowData = sheet.getRow(listRowIndex);
        cellData = rowData.getCell(dataColNumber);
        germplasmList.setTitle(ExcelUtils.getStringValueFromCell(cellData));

        log.info("Data for Germplasm List: " + germplasmList.toString());

    }

    private void fillEntryList(GermplasmList germplasmList, org.apache.poi.ss.usermodel.Workbook excelBook) {
        List<ListOfEntries> listEntries = new ArrayList<ListOfEntries>();
        // assume values are in B colum (index 1)
        //int entryRowIndex = ROW_HEADER_INDEX + 1;
        int entryRowIndex = rowHeader + 1;
        int lastEntryRowIndex = getLastEntryRowIndex(excelBook);


        // Assume that all values are in first sheet
        Sheet sheet = excelBook.getSheetAt(sheetNumber);
        Cell cellData = null;
        Row rowData = null;

        // internal index for count number of items
        int rowCounter = 1;

        for (int rowIndex = entryRowIndex; rowIndex < lastEntryRowIndex; rowIndex++) {

            rowData = sheet.getRow(rowIndex);
            if (rowData != null) {

                // checks if all values are filled for a row

                // create a new FACTOR
                ListOfEntries listEntry = new ListOfEntries();

                // assign the curren index (number of row in file)
                listEntry.setNumber(rowCounter);

                // assign data
                cellData = rowData.getCell(COLUMN_GID);
                listEntry.setGid(ExcelUtils.getIntValueFromCell(cellData));

                cellData = rowData.getCell(COLUMN_ENTRY_CODE);
                listEntry.setEntryCode(ExcelUtils.getStringValueFromCell(cellData));

                cellData = rowData.getCell(COLUMN_DESIGNATION);
                String designation = ExcelUtils.getStringValueFromCell(cellData);
                // disable name standarization
                //if (designation != null && ! designation.isEmpty()) {
                //    designation = org.generationcp.middleware.manager.GermplasmDataManagerImpl.standardaizeName(designation);
                //}
                listEntry.setDesignation(designation);

                cellData = rowData.getCell(COLUMN_CROSS);
                listEntry.setCross(ExcelUtils.getStringValueFromCell(cellData));

                cellData = rowData.getCell(COLUMN_SOURCE);
                listEntry.setSource(ExcelUtils.getStringValueFromCell(cellData));

                cellData = rowData.getCell(COLUMN_UNIQUE_ID);
                listEntry.setUniqueId(ExcelUtils.getStringValueFromCell(cellData));

                cellData = rowData.getCell(COLUMN_ENTRY_ID);
                listEntry.setEntryId(ExcelUtils.getIntValueFromCell(cellData));


                //log.info("Data for Entry: " + listEntry.toString());
                // add readed Entry to list
                listEntries.add(listEntry);
            }

            rowCounter++;
        }

        log.info("Total liet entries found: " + listEntries.size());

        germplasmList.setListEntries(listEntries);
    }

    /**
     *
     * @param germplasmList
     * @param excelBook
     */
    private void fillEntryListUsingFactors(GermplasmList germplasmList, org.apache.poi.ss.usermodel.Workbook excelBook) {
        List<ListOfEntries> listEntries = new ArrayList<ListOfEntries>();
        // assume values are in B colum (index 1)
        //int entryRowIndex = ROW_HEADER_INDEX + 1;
        int entryRowIndex = rowHeader + 1;
        int lastEntryRowIndex = getLastEntryRowIndex(excelBook);


        // Assume that all values are in first sheet
        Sheet sheet = excelBook.getSheetAt(sheetNumber);
        Cell cellData = null;
        Row rowData = null;

        // internal index for count number of items
        int rowCounter = 1;




        for (int rowIndex = entryRowIndex; rowIndex < lastEntryRowIndex; rowIndex++) {

            rowData = sheet.getRow(rowIndex);
            if (rowData != null) {

                // checks if all values are filled for a row

                // create a new FACTOR
                ListOfEntries listEntry = new ListOfEntries();

                // assign the curren index (number of row in file)
                listEntry.setNumber(rowCounter);

                // assign data
                cellData = rowData.getCell(gidHeaderIndex);
                listEntry.setGid(ExcelUtils.getIntValueFromCell(cellData));

                if (entryCodeHeaderIndex != null) {
                    cellData = rowData.getCell(entryCodeHeaderIndex);
                    listEntry.setEntryCode(ExcelUtils.getStringValueFromCell(cellData));
                }

                cellData = rowData.getCell(designationHeaderIndex);
                String designation = ExcelUtils.getStringValueFromCell(cellData);
                // disable name standarization
                //if (designation != null && ! designation.isEmpty()) {
                //    designation = org.generationcp.middleware.manager.GermplasmDataManagerImpl.standardaizeName(designation);
                //}
                listEntry.setDesignation(designation);

                if (crossHeaderIndex != null) {
                    cellData = rowData.getCell(crossHeaderIndex);
                    listEntry.setCross(ExcelUtils.getStringValueFromCell(cellData));
                }

                if (sourceHeaderIndex != null) {
                    cellData = rowData.getCell(sourceHeaderIndex);
                    listEntry.setSource(ExcelUtils.getStringValueFromCell(cellData));
                }

                //cellData = rowData.getCell(COLUMN_UNIQUE_ID);
                //listEntry.setUniqueId(ExcelUtils.getStringValueFromCell(cellData));

                cellData = rowData.getCell(entryIdHeaderIndex);
                listEntry.setEntryId(ExcelUtils.getIntValueFromCell(cellData));


                //log.info("Data for Entry: " + listEntry.toString());
                // add readed Entry to list
                listEntries.add(listEntry);
            }

            rowCounter++;
        }

        log.info("Total liet entries found: " + listEntries.size());

        germplasmList.setListEntries(listEntries);
    }

    /**
     * Return last Row Index where list contains data
     *
     * @return
     */
    private int getLastEntryRowIndex(org.apache.poi.ss.usermodel.Workbook excelBook) {
        int entryRowIndex = ROW_HEADER_INDEX + 1;
        int lastEntryRowIndex = entryRowIndex;
        boolean moreRowsToRead = true;

        Sheet sheet = excelBook.getSheetAt(sheetNumber);

        Row rowData = null;

        int entryColumnIndex = COLUMN_ENTRY_ID;
        if (hasEntryFactors()) {
            entryColumnIndex = entryIdHeaderIndex;
        }

        while (moreRowsToRead) {
            lastEntryRowIndex++;
            rowData = sheet.getRow(lastEntryRowIndex);

            moreRowsToRead = ExcelUtils.isMoreRows(rowData, entryColumnIndex);

            if (!moreRowsToRead) {
                break;
            }

        }
        return lastEntryRowIndex;
    }

    /**
     * Gets row number where GID header is located in spreadshet
     *
     * @param excelBook
     * @return A row number greater than -1 if GID label found or -1 if not
     * found
     */
    private int getRowHeaderIndex(org.apache.poi.ss.usermodel.Workbook excelBook) {
        int gidRowIndex = -1;

        Sheet sheet = excelBook.getSheetAt(sheetNumber);
        Cell cellData = null;
        Row rowData = null;
        int gidColumn = COLUMN_GID;
        String gidTextValue = HEADER_GID;

        // if versino of template
        if (hasEntryFactors()) {
            gidColumn = gidHeaderIndex;
            gidTextValue = entryFactors.get(gidHeaderIndex).getFactorName();
        }

        for (int index = 0; index < MAX_ROW; index++) {

            rowData = sheet.getRow(index);
            if (rowData != null) {
                cellData = rowData.getCell(gidColumn);
                String cell = ExcelUtils.getStringValueFromCell(cellData);

                if (cell.equals(gidTextValue)) {
                    gidRowIndex = index;
                    break;
                }
            }
        }
        return gidRowIndex;

    }

    /**
     * @autor tmsanchez
     *
     * @param listid
     * @return
     */
    @Override
    public GermplasmList getGermPlasmListFromDB(Integer listid) {
        GermplasmList germplasmList = new GermplasmList();

//        Listnms listnms = AppServicesProxy.getDefault().appServices().getFullListnms(listid);

        fillHeader(germplasmList, listid);
        fillEntryList(germplasmList, listid);


        return germplasmList;
    }

    /**
     * Fill header list from DB
     *
     * @param germplasmList GermplasmList to fill
     * @param listid ID for germplasm list
     */
    private void fillHeader(GermplasmList germplasmList, Integer listid) {

        ///* PENDIENTE 

        // retrieve list from DB

        Listnms listnms = AppServicesProxy.getDefault().appServices().getListnms(listid);

        // Assign from DB
        germplasmList.setId(listid);
        germplasmList.setName(listnms.getListname());
        germplasmList.setTitle(listnms.getListdesc());
//*/
    }

    /**
     * Fills a GermplasmList from all items in DB
     *
     * @param germplasmList GermplasmList to fill
     * @param listid
     */
    private void fillEntryList(GermplasmList germplasmList, Integer listid) {

        ///* PENDIENTE 
        List<ListOfEntries> listEntries = new ArrayList<ListOfEntries>();

        // Define search filter
        Listdata filter = new Listdata(true);
        filter.setListdataPK(new ListdataPK(listid, null));
        //int total = AppServicesProxy.getDefault().appServices().getTotalListdata(filter);
        // Retrieve list data for listId
        Listnms listnms = AppServicesProxy.getDefault().appServices().getFullListnms(listid);
        List<Listdata> listdataList = listnms.getLisdatas();


        int rowCounter = 1;

        // Then assign to list of entries
        for (Listdata listdata : listdataList) {
            ListOfEntries entries = new ListOfEntries();
            // assing from DB
            entries.setCross(listdata.getGrpname());
            entries.setDesignation(listdata.getDesig());
            entries.setEntryId(listdata.getEntryid());
            entries.setSource(listdata.getSource());
            entries.setEntryCode(listdata.getEntrycd());
            entries.setGid(listdata.getGid());

            WheatData wheatData = listdata.getWheatData();
            if (wheatData != null) {
                wheatData.setBcid(listdata.getName1027() != null && listdata.getName1027().getNval() != null ? listdata.getName1027().getNval() : "");
                wheatData.setCrossName(listdata.getName1029() != null && listdata.getName1029().getNval() != null ? listdata.getName1029().getNval() : "");
                wheatData.setSelectionHistory(listdata.getName1028() != null && listdata.getName1028().getNval() != null ? listdata.getName1028().getNval() : "");

                entries.setWheatData(wheatData);
            }

            entries.setNumber(rowCounter);


            // add to entries list
            listEntries.add(entries);

            // increment row counter
            rowCounter++;
        }
        germplasmList.setListEntries(listEntries);


        // */
    }

    /**
     * Assign sheet number where Germplasm list is containted
     *
     * @param sheetNumber
     */
    @Override
    public void setSheetNumberForGermplasm(Integer sheetNumber) {
        this.sheetNumber = sheetNumber;
    }

    /**
     * Assign sheet namn where Germplasm list is containted
     *
     * @param sheetNumber
     */
    @Override
    public void setSheetNameForGermplasm(String sheetName) {
        this.sheetName = sheetName;
    }

    /**
     * Assign the list of factors header to read excel template
     *
     * @param factorHeader
     */
    @Override
    public void setEntryFactors(List<Factor> entryFactors) {
        this.entryFactors = entryFactors;
        columnMap = new HashMap<String, Integer>();
        int columnIndex = 0;
        for (Factor factor : entryFactors) {
            columnMap.put(Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale()), columnIndex);
            columnIndex++;
        }
        gidHeaderIndex = columnMap.get(Workbook.GERMPLASM_ID_DBID);
        entryCodeHeaderIndex = columnMap.get(Workbook.GERMPLASM_ENTRY_CODE);
        designationHeaderIndex = columnMap.get(Workbook.GERMPLASM_ID_DBCV);
        crossHeaderIndex = columnMap.get(Workbook.CROSS_HISTORY_NAME);
        sourceHeaderIndex = columnMap.get(Workbook.SEED_SOURCE_NAME);
        entryIdHeaderIndex = columnMap.get(Workbook.GERMPLASM_ENTRY_NUMBER);
    }

    private boolean hasEntryFactors() {
        return !this.entryFactors.isEmpty();
    }
}
