package com.alphatrader.rest;

import javafx.beans.property.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Represents a bond in the game. Contains factory methods to handle API communication as well.
 *
 * @author Christopher Guckes (christopher.guckes@torq-dev.de)
 * @version 1.0
 */
public class Bond {
    /**
     * The bond's name.
     */
    private final StringProperty name = new SimpleStringProperty();

    /**
     * The bond's volume.
     */
    private final DoubleProperty volume = new SimpleDoubleProperty();

    /**
     * The overall interest rate of the bond.
     */
    private final DoubleProperty interestRate = new SimpleDoubleProperty();

    /**
     * The face value the bond was issued for.
     */
    private final DoubleProperty faceValue = new SimpleDoubleProperty();

    /**
     * The bond's date of maturity.
     */
    private final ObjectProperty<ZonedDateTime> maturityDate = new SimpleObjectProperty<>();

    /**
     * The date the bond was issued.
     */
    private final ObjectProperty<ZonedDateTime> issueDate = new SimpleObjectProperty<>();

    /**
     * The price spread
     */
    private final ObjectProperty<PriceSpread> priceSpread = new SimpleObjectProperty<>();

    /**
     * The company that issued the bond.
     */
    private final ObjectProperty<Company> issuer = new SimpleObjectProperty<>();

    /**
     * The listing as present on the market.
     */
    private final ObjectProperty<Listing> listing = new SimpleObjectProperty<>();

    /**
     * The repurchase listing as present on the market.
     */
    private final ObjectProperty<Listing> repurchaseListing = new SimpleObjectProperty<>();

    /**
     * The unique bond id.
     */
    private final StringProperty id = new SimpleStringProperty();

    /**
     * Fetches all bonds currently on the market from the server.
     *
     * @return all bonds on the market
     */
    @PublicAPI
    @NotNull
    public static List<Bond> getAllBonds() {
        return getMultipleBondsFromApi("bonds/");
    }

    /**
     * Fetches all system bonds on the market from the server.
     *
     * @return all system bonds on the market
     */
    @PublicAPI
    @NotNull
    public static List<Bond> getAllSystemBonds() {
        return getMultipleBondsFromApi("systembonds/");
    }

    /**
     * Fetches the bond with the given security identifier from the server.
     *
     * @param secId the security identifier of the bond you want
     * @return the bond with the given security identifier
     */
    @PublicAPI
    @Nullable
    public static Bond getBondBySecurityIdentifier(String secId) {
        return getSingleBondFromApi("bonds/securityidentifier/" + secId);
    }

    /**
     * Fetches the system bond with the given security identifier from the server.
     *
     * @param secId the security identifier of the system bond you want
     * @return the system bond with the given security identifier
     */
    @PublicAPI
    @Nullable
    public static Bond getSystemBondBySecurityIdentifier(String secId) {
        return getSingleBondFromApi("systembonds/securityidentifier/" + secId);
    }

    /**
     * Fetches the bond with the given id from the server.
     *
     * @param bondId the id of the bond you want
     * @return the bond with the given id
     */
    @PublicAPI
    @Nullable
    public static Bond getBondById(String bondId) {
        return getSingleBondFromApi("bonds/" + bondId);
    }

    /**
     * Fetches the system bond with the given id from the server.
     *
     * @param bondId the id of the system bond you want
     * @return the system bond with the given id
     */
    @PublicAPI
    @Nullable
    public static Bond getSystemBondById(String bondId) {
        return getSingleBondFromApi("systembonds/" + bondId);
    }

    /**
     * Wrapper function fetching single bonds from the API.
     *
     * @param suffix the api endpoint suffix
     * @return the requested bond or null of none found.
     */
    @Nullable
    private static Bond getSingleBondFromApi(String suffix) {
        return Http.getSingleObjectFromApi(Bond.class, "/api/" + suffix);
    }

    /**
     * Wrapper function for fetching multiple Bonds from the API.
     *
     * @param suffix the api endpoint suffix
     * @return all requested bonds
     */
    @PublicAPI
    @NotNull
    public static List<Bond> getMultipleBondsFromApi(String suffix) {
        return Http.getMultipleObjectFromApi(Bond.class, "/api/" + suffix);
    }

    /**
     * @return the name
     */
    @PublicAPI
    public String getName() {
        return name.getValue();
    }

    /**
     * @return the name property
     */
    @PublicAPI
    public ReadOnlyStringProperty nameProperty() {
        return name;
    }

    /**
     * @return the volume
     */
    @PublicAPI
    public Double getVolume() {
        return volume.getValue();
    }

    /**
     * @return the volume property
     */
    @PublicAPI
    public ReadOnlyDoubleProperty volumeProperty() {
        return volume;
    }

    /**
     * @return the interest rate
     */
    @PublicAPI
    public Double getInterestRate() {
        return interestRate.getValue();
    }

    /**
     * @return the interest rate property
     */
    @PublicAPI
    public ReadOnlyDoubleProperty interestRateProperty() {
        return interestRate;
    }

    /**
     * @return the face value
     */
    @PublicAPI
    public Double getFaceValue() {
        return faceValue.getValue();
    }

    /**
     * @return the face value property
     */
    @PublicAPI
    public ReadOnlyDoubleProperty faceValueProperty() {
        return faceValue;
    }

    /**
     * @return the maturity date
     */
    @PublicAPI
    public ZonedDateTime getMaturityDate() {
        return maturityDate.getValue();
    }

    /**
     * @return the maturity date property
     */
    @PublicAPI
    public ReadOnlyObjectProperty<ZonedDateTime> maturityDateProperty() {
        return maturityDate;
    }

    /**
     * @return the issue date
     */
    @PublicAPI
    public ZonedDateTime getIssueDate() {
        return issueDate.getValue();
    }

    /**
     * @return the issue date property
     */
    @PublicAPI
    public ReadOnlyObjectProperty<ZonedDateTime> issueDateProperty() {
        return issueDate;
    }

    /**
     * @return the price spread
     */
    @PublicAPI
    public PriceSpread getPriceSpread() {
        return priceSpread.getValue();
    }

    /**
     * @return the price spread property
     */
    @PublicAPI
    public ReadOnlyObjectProperty<PriceSpread> priceSpreadProperty() {
        return priceSpread;
    }

    /**
     * @return the issuer
     */
    @PublicAPI
    public Company getIssuer() {
        return issuer.getValue();
    }

    /**
     * @return the issuer property
     */
    @PublicAPI
    public ReadOnlyObjectProperty<Company> issuerProperty() {
        return issuer;
    }

    /**
     * @return the listing
     */
    @PublicAPI
    public Listing getListing() {
        return listing.getValue();
    }

    /**
     * @return the listing property
     */
    @PublicAPI
    public ReadOnlyObjectProperty<Listing> listingProperty() {
        return listing;
    }

    /**
     * @return the repurchase listing
     */
    @PublicAPI
    public Listing getRepurchaseListing() {
        return repurchaseListing.getValue();
    }

    /**
     * @return the repurchase listing property
     */
    @PublicAPI
    public ReadOnlyObjectProperty<Listing> repurchaseListingProperty() {
        return repurchaseListing;
    }

    /**
     * @return the unique id
     */
    @PublicAPI
    public String getId() {
        return id.getValue();
    }

    /**
     * @return the unique id property
     */
    @PublicAPI
    public ReadOnlyStringProperty idProperty() {
        return id;
    }

    @Override
    public String toString() {
        return "Bond{"
            + "name='" + name.getValue() + '\''
            + ", volume=" + volume.getValue()
            + ", interestRate=" + interestRate.getValue()
            + ", faceValue=" + faceValue.getValue()
            + ", maturityDate=" + maturityDate.getValue()
            + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Bond bond = (Bond) o;

        return id.getValue() != null ? id.getValue().equals(bond.id.getValue())
            : bond.id.getValue() == null;
    }

    @Override
    public int hashCode() {
        int result = name.getValue() != null ? name.getValue().hashCode() : 0;
        result = 31 * result + (id.getValue() != null ? id.getValue().hashCode() : 0);
        return result;
    }
}
