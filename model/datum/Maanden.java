package model.datum;

public enum Maanden {
	januari(1, 31),
	februari(2, 28),
	maart(3, 31),
	april(4, 30),
	mei(5, 31),
	juni(6, 30),
	juli(7, 31),
	augustus(8, 31),
	september(9, 30),
	oktober(10, 31),
	november(11, 30),
	december(12, 31);

	private final int maandNummer;
	private final int aantalDagen;

	/**
	 * Constructor
	 *
	 * @param maandNummer
	 * @param aantalDagen
	 */
	Maanden(int maandNummer, int aantalDagen)
	{
		this.maandNummer = maandNummer;
		this.aantalDagen = aantalDagen;
	}

	/**
	 * Haal de maand uit de enum o.b.v. de numerieke waarde
	 *
	 * @param maandNummer
	 * @return Maanden object of null wanneer niet gevonden
	 */
	public static Maanden get(int maandNummer)
	{
		for (Maanden maand : Maanden.values())
		{
			if (maand.maandNummer == maandNummer)
			{
				return maand;
			}
		}
		return null;
	}

	/**
	 * De numerieke waarde van de maand
	 *
	 * @return
	 */
	public int maandNummer()
	{
		return maandNummer;
	}

	/**
	 *
	 *
	 * @param maandNummer
	 * @return
	 */
	public int aantalDagen()
	{
		return aantalDagen;
	}


	/**
	 * 
	 * @param jaar
	 * @return
	 */
	public int aantalDagen(int jaar)
	{
		return (isSchrikkeljaar(jaar) && februari.maandNummer() == maandNummer)
				? 29
				: aantalDagen;

	}

    /**
     * Is het gegeven jaar een schrikkeljaar?
     *
     * @param jaar
     * @return
     */
    public static boolean isSchrikkeljaar(int jaar)
    {
        return (jaar % 4 == 0 && jaar % 100 != 0) || (jaar % 400 == 0) ? true : false;
    }

}
