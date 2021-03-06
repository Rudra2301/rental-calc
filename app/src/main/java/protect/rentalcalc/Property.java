package protect.rentalcalc;

import android.database.Cursor;
import android.util.Log;

import java.lang.reflect.Field;

class Property
{
    long id;
    String nickname;
    String addressStreet;
    String addressCity;
    String addressState;
    String addressZip;
    int propertyType;
    String propertyBeds;
    String propertyBaths;
    int propertySqft;
    int propertyLot;
    int propertyYear;
    int propertyParking;
    String propertyZoning;
    String propertyMls;
    int purchasePrice;
    int afterRepairsValue;
    boolean useLoan;
    int downPayment;
    double interestRate;
    int loanDuration;
    int purchaseCosts;
    int repairRemodelCosts;
    int grossRent;
    int otherIncome;
    int expenses;
    int vacancy;
    int appreciation;
    int incomeIncrease;
    int expenseIncrease;
    int sellingCosts;
    int landValue;
    int incomeTaxRate;
    String notes;

    private static String toBlankIfNull(final String string)
    {
        if(string != null)
        {
            return string;
        }
        else
        {
            return "";
        }
    }

    Property()
    {
        // Default all strings to "" instead of null
        for(Field field : Property.class.getDeclaredFields())
        {
            if(field.getType() == String.class)
            {
                try
                {
                    field.set(this, "");
                }
                catch (IllegalAccessException e)
                {
                    Log.e("RentalCal", "Failed to assign field", e);
                }
            }
        }

        // Fill in default values

        propertyBeds = "1";
        propertyBaths = "1";
        useLoan = true;
        downPayment = 20; // 20%
        interestRate = 5; // 5%
        loanDuration = 30; // 30 years
        purchaseCosts = 3; // 3 %
        repairRemodelCosts = 0; // 0 %
        grossRent = 0; // $0/month
        otherIncome = 0; // $0/month
        expenses = 30; // 30% of rent
        vacancy = 10; // 10% vacancy rate
        appreciation = 3; // 3% appreciation per year
        incomeIncrease = 2; // 2% increase per year
        expenseIncrease = 2;
        sellingCosts = 6; // 6% of sale price
        landValue = 0; // cost of the land
        incomeTaxRate = 25; // 25% tax rate
    }

    Property(final Property original)
    {
        for(Field field : Property.class.getDeclaredFields())
        {
            try
            {
                Object data = field.get(original);
                field.set(this, data);
            }
            catch (IllegalAccessException e)
            {
                Log.e("RentalCal", "Failed to assign field", e);
            }
        }
    }

    static Property toProperty(Cursor cursor)
    {
        Property property = new Property();
        property.id = cursor.getLong(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.ID));
        property.nickname = toBlankIfNull(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.NICKNAME)));
        property.addressStreet = toBlankIfNull(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.ADDRESS_STREET)));
        property.addressCity  = toBlankIfNull(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.ADDRESS_CITY)));
        property.addressState = toBlankIfNull(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.ADDRESS_STATE)));
        property.addressZip = toBlankIfNull(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.ADDRESS_ZIP)));
        property.propertyType = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.PROPERTY_TYPE));
        property.propertyBeds = toBlankIfNull(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.PROPERTY_BEDS)));
        property.propertyBaths = toBlankIfNull(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.PROPERTY_BATHS)));
        property.propertySqft = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.PROPERTY_SQUARE_FOOTAGE));
        property.propertyLot = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.PROPERTY_LOT));
        property.propertyYear = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.PROPERTY_YEAR));
        property.propertyParking = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.PROPERTY_PARKING));
        property.propertyZoning = toBlankIfNull(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.PROPERTY_ZONING)));
        property.propertyMls = toBlankIfNull(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.PROPERTY_MLS)));
        property.purchasePrice = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.PURCHASE_PRICE));
        property.afterRepairsValue = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.AFTER_REPAIRS_VALUE));
        property.useLoan = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.USE_LOAN)) > 0;
        property.downPayment = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.DOWN_PAYMENT));
        property.interestRate = cursor.getDouble(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.INTEREST_RATE));
        property.loanDuration = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.LOAN_DURATION));
        property.purchaseCosts = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.PURCHASE_COSTS));
        property.repairRemodelCosts = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.REPAIR_REMODEL_COSTS));
        property.grossRent = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.GROSS_RENT));
        property.otherIncome = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.OTHER_INCOME));
        property.expenses = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.EXPENSES));
        property.vacancy = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.VACANCY));
        property.appreciation = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.APPRECIATION));
        property.incomeIncrease = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.INCOME_INCREASE));
        property.expenseIncrease = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.EXPENSE_INCREASE));
        property.sellingCosts = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.SELLING_COSTS));
        property.landValue = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.LAND_VALUE));
        property.incomeTaxRate = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.INCOME_TAX_RATE));
        property.notes = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PropertyDbIds.NOTES));

        return property;
    }
}