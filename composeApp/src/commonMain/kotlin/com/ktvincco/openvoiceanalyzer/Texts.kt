package com.ktvincco.openvoiceanalyzer


class TextForReading (
    private val headline: String,
    private val text: String
) {
    fun getHeadline(): String {
        return headline
    }

    fun getText(): String {
        return text + "\n\n\n\n\n\n\n\n\n\n\n\n"
    }
}


class ParameterInfo (
    private val parameterId: String,
    private val parameterName: String,
    private val description: String
) {
    fun getParameterId(): String {
        return parameterId
    }

    fun getParameterName(): String {
        return parameterName
    }

    fun getDescription(): String {
        return description
    }
}


class Texts (
    private val headline: String,
    private val text: String
) {
    companion object {


        private val textsFarReadingArray = arrayOf(


            TextForReading(
                "Universal Declaration of Human Rights",
                "Preamble\n" +
                        "\n" +
                        "Whereas recognition of the inherent dignity and of the equal and inalienable rights of all members of the human family is the foundation of freedom, justice and peace in the world,\n" +
                        "\n" +
                        "Whereas disregard and contempt for human rights have resulted in barbarous acts which have outraged the conscience of mankind, and the advent of a world in which human beings shall enjoy freedom of speech and belief and freedom from fear and want has been proclaimed as the highest aspiration of the common people,\n" +
                        "\n" +
                        "Whereas it is essential, if man is not to be compelled to have recourse, as a last resort, to rebellion against tyranny and oppression, that human rights should be protected by the rule of law,\n" +
                        "\n" +
                        "Whereas it is essential to promote the development of friendly relations between nations,\n" +
                        "\n" +
                        "Whereas the peoples of the United Nations have in the Charter reaffirmed their faith in fundamental human rights, in the dignity and worth of the human person and in the equal rights of men and women and have determined to promote social progress and better standards of life in larger freedom,\n" +
                        "\n" +
                        "Whereas Member States have pledged themselves to achieve, in co-operation with the United Nations, the promotion of universal respect for and observance of human rights and fundamental freedoms,\n" +
                        "\n" +
                        "Whereas a common understanding of these rights and freedoms is of the greatest importance for the full realization of this pledge,\n" +
                        "\n" +
                        "Now, therefore,\n" +
                        "\n" +
                        "The General Assembly,\n" +
                        "\n" +
                        "Proclaims this Universal Declaration of Human Rights as a common standard of achievement for all peoples and all nations, to the end that every individual and every organ of society, keeping this Declaration constantly in mind, shall strive by teaching and education to promote respect for these rights and freedoms and by progressive measures, national and international, to secure their universal and effective recognition and observance, both among the peoples of Member States themselves and among the peoples of territories under their jurisdiction. \n" +
                        "\n" +
                        "Article 1\n" +
                        "\n" +
                        "All human beings are born free and equal in dignity and rights. They are endowed with reason and conscience and should act towards one another in a spirit of brotherhood.\n" +
                        "\n" +
                        "Article 2\n" +
                        "\n" +
                        "Everyone is entitled to all the rights and freedoms set forth in this Declaration, without distinction of any kind, such as race, colour, sex, language, religion, political or other opinion, national or social origin, property, birth or other status. Furthermore, no distinction shall be made on the basis of the political, jurisdictional or international status of the country or territory to which a person belongs, whether it be independent, trust, non-self-governing or under any other limitation of sovereignty.\n" +
                        "\n" +
                        "Article 3\n" +
                        "\n" +
                        "Everyone has the right to life, liberty and security of person.\n" +
                        "\n" +
                        "Article 4\n" +
                        "\n" +
                        "No one shall be held in slavery or servitude; slavery and the slave trade shall be prohibited in all their forms.\n" +
                        "\n" +
                        "Article 5\n" +
                        "\n" +
                        "No one shall be subjected to torture or to cruel, inhuman or degrading treatment or punishment.\n" +
                        "\n" +
                        "Article 6\n" +
                        "\n" +
                        "Everyone has the right to recognition everywhere as a person before the law.\n" +
                        "\n" +
                        "Article 7\n" +
                        "\n" +
                        "All are equal before the law and are entitled without any discrimination to equal protection of the law. All are entitled to equal protection against any discrimination in violation of this Declaration and against any incitement to such discrimination.\n" +
                        "\n" +
                        "Article 8\n" +
                        "\n" +
                        "Everyone has the right to an effective remedy by the competent national tribunals for acts violating the fundamental rights granted him by the constitution or by law.\n" +
                        "\n" +
                        "Article 9\n" +
                        "\n" +
                        "No one shall be subjected to arbitrary arrest, detention or exile.\n" +
                        "\n" +
                        "Article 10\n" +
                        "\n" +
                        "Everyone is entitled in full equality to a fair and public hearing by an independent and impartial tribunal, in the determination of his rights and obligations and of any criminal charge against him.\n" +
                        "\n" +
                        "Article 11\n" +
                        "\n" +
                        "1. Everyone charged with a penal offence has the right to be presumed innocent until proved guilty according to law in a public trial at which he has had all the guarantees necessary for his defence.\n" +
                        "2. No one shall be held guilty of any penal offence on account of any act or omission which did not constitute a penal offence, under national or international law, at the time when it was committed. Nor shall a heavier penalty be imposed than the one that was applicable at the time the penal offence was committed.\n" +
                        "\n" +
                        "Article 12\n" +
                        "\n" +
                        "No one shall be subjected to arbitrary interference with his privacy, family, home or correspondence, nor to attacks upon his honour and reputation. Everyone has the right to the protection of the law against such interference or attacks.\n" +
                        "\n" +
                        "Article 13\n" +
                        "\n" +
                        "1. Everyone has the right to freedom of movement and residence within the borders of each state.\n" +
                        "2. Everyone has the right to leave any country, including his own, and to return to his country.\n" +
                        "\n" +
                        "Article 14\n" +
                        "\n" +
                        "1. Everyone has the right to seek and to enjoy in other countries asylum from persecution.\n" +
                        "2. This right may not be invoked in the case of prosecutions genuinely arising from non-political crimes or from acts contrary to the purposes and principles of the United Nations.\n" +
                        "\n" +
                        "Article 15\n" +
                        "\n" +
                        "1. Everyone has the right to a nationality.\n" +
                        "2. No one shall be arbitrarily deprived of his nationality nor denied the right to change his nationality.\n" +
                        "\n" +
                        "Article 16\n" +
                        "\n" +
                        "1. Men and women of full age, without any limitation due to race, nationality or religion, have the right to marry and to found a family. They are entitled to equal rights as to marriage, during marriage and at its dissolution.\n" +
                        "2. Marriage shall be entered into only with the free and full consent of the intending spouses.\n" +
                        "3. The family is the natural and fundamental group unit of society and is entitled to protection by society and the State.\n" +
                        "\n" +
                        "Article 17\n" +
                        "\n" +
                        "1. Everyone has the right to own property alone as well as in association with others.\n" +
                        "2. No one shall be arbitrarily deprived of his property.\n" +
                        "\n" +
                        "Article 18\n" +
                        "\n" +
                        "Everyone has the right to freedom of thought, conscience and religion; this right includes freedom to change his religion or belief, and freedom, either alone or in community with others and in public or private, to manifest his religion or belief in teaching, practice, worship and observance.\n" +
                        "\n" +
                        "Article 19\n" +
                        "\n" +
                        "Everyone has the right to freedom of opinion and expression; this right includes freedom to hold opinions without interference and to seek, receive and impart information and ideas through any media and regardless of frontiers.\n" +
                        "\n" +
                        "Article 20\n" +
                        "\n" +
                        "1. Everyone has the right to freedom of peaceful assembly and association.\n" +
                        "2. No one may be compelled to belong to an association.\n" +
                        "\n" +
                        "Article 21\n" +
                        "\n" +
                        "1. Everyone has the right to take part in the government of his country, directly or through freely chosen representatives.\n" +
                        "2. Everyone has the right of equal access to public service in his country.\n" +
                        "3. The will of the people shall be the basis of the authority of government; this will shall be expressed in periodic and genuine elections which shall be by universal and equal suffrage and shall be held by secret vote or by equivalent free voting procedures.\n" +
                        "\n" +
                        "Article 22\n" +
                        "\n" +
                        "Everyone, as a member of society, has the right to social security and is entitled to realization, through national effort and international co-operation and in accordance with the organization and resources of each State, of the economic, social and cultural rights indispensable for his dignity and the free development of his personality.\n" +
                        "\n" +
                        "Article 23\n" +
                        "\n" +
                        "1. Everyone has the right to work, to free choice of employment, to just and favourable conditions of work and to protection against unemployment.\n" +
                        "2. Everyone, without any discrimination, has the right to equal pay for equal work.\n" +
                        "3. Everyone who works has the right to just and favourable remuneration ensuring for himself and his family an existence worthy of human dignity, and supplemented, if necessary, by other means of social protection.\n" +
                        "4. Everyone has the right to form and to join trade unions for the protection of his interests.\n" +
                        "\n" +
                        "Article 24\n" +
                        "\n" +
                        "Everyone has the right to rest and leisure, including reasonable limitation of working hours and periodic holidays with pay.\n" +
                        "\n" +
                        "Article 25\n" +
                        "\n" +
                        "1. Everyone has the right to a standard of living adequate for the health and well-being of himself and of his family, including food, clothing, housing and medical care and necessary social services, and the right to security in the event of unemployment, sickness, disability, widowhood, old age or other lack of livelihood in circumstances beyond his control.\n" +
                        "2. Motherhood and childhood are entitled to special care and assistance. All children, whether born in or out of wedlock, shall enjoy the same social protection.\n" +
                        "\n" +
                        "Article 26\n" +
                        "\n" +
                        "1. Everyone has the right to education. Education shall be free, at least in the elementary and fundamental stages. Elementary education shall be compulsory. Technical and professional education shall be made generally available and higher education shall be equally accessible to all on the basis of merit.\n" +
                        "2. Education shall be directed to the full development of the human personality and to the strengthening of respect for human rights and fundamental freedoms. It shall promote understanding, tolerance and friendship among all nations, racial or religious groups, and shall further the activities of the United Nations for the maintenance of peace.\n" +
                        "3. Parents have a prior right to choose the kind of education that shall be given to their children.\n" +
                        "\n" +
                        "Article 27\n" +
                        "\n" +
                        "1. Everyone has the right freely to participate in the cultural life of the community, to enjoy the arts and to share in scientific advancement and its benefits.\n" +
                        "2. Everyone has the right to the protection of the moral and material interests resulting from any scientific, literary or artistic production of which he is the author.\n" +
                        "\n" +
                        "Article 28\n" +
                        "\n" +
                        "Everyone is entitled to a social and international order in which the rights and freedoms set forth in this Declaration can be fully realized.\n" +
                        "\n" +
                        "Article 29\n" +
                        "\n" +
                        "1. Everyone has duties to the community in which alone the free and full development of his personality is possible.\n" +
                        "2. In the exercise of his rights and freedoms, everyone shall be subject only to such limitations as are determined by law solely for the purpose of securing due recognition and respect for the rights and freedoms of others and of meeting the just requirements of morality, public order and the general welfare in a democratic society.\n" +
                        "3. These rights and freedoms may in no case be exercised contrary to the purposes and principles of the United Nations.\n" +
                        "\n" +
                        "Article 30\n" +
                        "\n" +
                        "Nothing in this Declaration may be interpreted as implying for any State, group or person any right to engage in any activity or to perform any act aimed at the destruction of any of the rights and freedoms set forth herein."
            ),


            TextForReading(
                "The Constitution of the United States",
                "We the People of the United States, in Order to form a more perfect Union, establish Justice, insure domestic Tranquility, provide for the common defence, promote the general Welfare, and secure the Blessings of Liberty to ourselves and our Posterity, do ordain and establish this Constitution for the United States of America.\n" +
                        "\n" +
                        "Article. I.\n" +
                        "\n" +
                        "Section. 1.\n" +
                        "\n" +
                        "All legislative Powers herein granted shall be vested in a Congress of the United States, which shall consist of a Senate and House of Representatives.\n" +
                        "\n" +
                        "Section. 2.\n" +
                        "\n" +
                        "The House of Representatives shall be composed of Members chosen every second Year by the People of the several States, and the Electors in each State shall have the Qualifications requisite for Electors of the most numerous Branch of the State Legislature.\n" +
                        "\n" +
                        "No Person shall be a Representative who shall not have attained to the Age of twenty five Years, and been seven Years a Citizen of the United States, and who shall not, when elected, be an Inhabitant of that State in which he shall be chosen.\n" +
                        "\n" +
                        "Representatives and direct Taxes shall be apportioned among the several States which may be included within this Union, according to their respective Numbers, which shall be determined by adding to the whole Number of free Persons, including those bound to Service for a Term of Years, and excluding Indians not taxed, three fifths of all other Persons. The actual Enumeration shall be made within three Years after the first Meeting of the Congress of the United States, and within every subsequent Term of ten Years, in such Manner as they shall by Law direct. The Number of Representatives shall not exceed one for every thirty Thousand, but each State shall have at Least one Representative; and until such enumeration shall be made, the State of New Hampshire shall be entitled to chuse three, Massachusetts eight, Rhode-Island and Providence Plantations one, Connecticut five, New-York six, New Jersey four, Pennsylvania eight, Delaware one, Maryland six, Virginia ten, North Carolina five, South Carolina five, and Georgia three.\n" +
                        "\n" +
                        "When vacancies happen in the Representation from any State, the Executive Authority thereof shall issue Writs of Election to fill such Vacancies.\n" +
                        "\n" +
                        "The House of Representatives shall chuse their Speaker and other Officers; and shall have the sole Power of Impeachment.\n" +
                        "\n" +
                        "Section. 3.\n" +
                        "\n" +
                        "The Senate of the United States shall be composed of two Senators from each State, chosen by the Legislature thereof, for six Years; and each Senator shall have one Vote.\n" +
                        "\n" +
                        "Immediately after they shall be assembled in Consequence of the first Election, they shall be divided as equally as may be into three Classes. The Seats of the Senators of the first Class shall be vacated at the Expiration of the second Year, of the second Class at the Expiration of the fourth Year, and of the third Class at the Expiration of the sixth Year, so that one third may be chosen every second Year; and if Vacancies happen by Resignation, or otherwise, during the Recess of the Legislature of any State, the Executive thereof may make temporary Appointments until the next Meeting of the Legislature, which shall then fill such Vacancies.\n" +
                        "\n" +
                        "No Person shall be a Senator who shall not have attained to the Age of thirty Years, and been nine Years a Citizen of the United States, and who shall not, when elected, be an Inhabitant of that State for which he shall be chosen.\n" +
                        "\n" +
                        "The Vice President of the United States shall be President of the Senate, but shall have no Vote, unless they be equally divided.\n" +
                        "\n" +
                        "The Senate shall chuse their other Officers, and also a President pro tempore, in the Absence of the Vice President, or when he shall exercise the Office of President of the United States.\n" +
                        "\n" +
                        "The Senate shall have the sole Power to try all Impeachments. When sitting for that Purpose, they shall be on Oath or Affirmation. When the President of the United States is tried, the Chief Justice shall preside: And no Person shall be convicted without the Concurrence of two thirds of the Members present.\n" +
                        "\n" +
                        "Judgment in Cases of Impeachment shall not extend further than to removal from Office, and disqualification to hold and enjoy any Office of honor, Trust or Profit under the United States: but the Party convicted shall nevertheless be liable and subject to Indictment, Trial, Judgment and Punishment, according to Law.\n" +
                        "\n" +
                        "Section. 4.\n" +
                        "\n" +
                        "The Times, Places and Manner of holding Elections for Senators and Representatives, shall be prescribed in each State by the Legislature thereof; but the Congress may at any time by Law make or alter such Regulations, except as to the Places of chusing Senators.\n" +
                        "\n" +
                        "The Congress shall assemble at least once in every Year, and such Meeting shall be on the first Monday in December, unless they shall by Law appoint a different Day.\n" +
                        "\n" +
                        "Section. 5.\n" +
                        "\n" +
                        "Each House shall be the Judge of the Elections, Returns and Qualifications of its own Members, and a Majority of each shall constitute a Quorum to do Business; but a smaller Number may adjourn from day to day, and may be authorized to compel the Attendance of absent Members, in such Manner, and under such Penalties as each House may provide.\n" +
                        "\n" +
                        "Each House may determine the Rules of its Proceedings, punish its Members for disorderly Behaviour, and, with the Concurrence of two thirds, expel a Member.\n" +
                        "\n" +
                        "Each House shall keep a Journal of its Proceedings, and from time to time publish the same, excepting such Parts as may in their Judgment require Secrecy; and the Yeas and Nays of the Members of either House on any question shall, at the Desire of one fifth of those Present, be entered on the Journal.\n" +
                        "\n" +
                        "Neither House, during the Session of Congress, shall, without the Consent of the other, adjourn for more than three days, nor to any other Place than that in which the two Houses shall be sitting.\n" +
                        "\n" +
                        "Section. 6.\n" +
                        "\n" +
                        "The Senators and Representatives shall receive a Compensation for their Services, to be ascertained by Law, and paid out of the Treasury of the United States. They shall in all Cases, except Treason, Felony and Breach of the Peace, be privileged from Arrest during their Attendance at the Session of their respective Houses, and in going to and returning from the same; and for any Speech or Debate in either House, they shall not be questioned in any other Place.\n" +
                        "\n" +
                        "No Senator or Representative shall, during the Time for which he was elected, be appointed to any civil Office under the Authority of the United States, which shall have been created, or the Emoluments whereof shall have been encreased during such time; and no Person holding any Office under the United States, shall be a Member of either House during his Continuance in Office.\n" +
                        "\n" +
                        "Section. 7.\n" +
                        "\n" +
                        "All Bills for raising Revenue shall originate in the House of Representatives; but the Senate may propose or concur with Amendments as on other Bills.\n" +
                        "\n" +
                        "Every Bill which shall have passed the House of Representatives and the Senate, shall, before it become a Law, be presented to the President of the United States; If he approve he shall sign it, but if not he shall return it, with his Objections to that House in which it shall have originated, who shall enter the Objections at large on their Journal, and proceed to reconsider it. If after such Reconsideration two thirds of that House shall agree to pass the Bill, it shall be sent, together with the Objections, to the other House, by which it shall likewise be reconsidered, and if approved by two thirds of that House, it shall become a Law. But in all such Cases the Votes of both Houses shall be determined by yeas and Nays, and the Names of the Persons voting for and against the Bill shall be entered on the Journal of each House respectively. If any Bill shall not be returned by the President within ten Days (Sundays excepted) after it shall have been presented to him, the Same shall be a Law, in like Manner as if he had signed it, unless the Congress by their Adjournment prevent its Return, in which Case it shall not be a Law.\n" +
                        "\n" +
                        "Every Order, Resolution, or Vote to which the Concurrence of the Senate and House of Representatives may be necessary (except on a question of Adjournment) shall be presented to the President of the United States; and before the Same shall take Effect, shall be approved by him, or being disapproved by him, shall be repassed by two thirds of the Senate and House of Representatives, according to the Rules and Limitations prescribed in the Case of a Bill.\n" +
                        "\n" +
                        "Section. 8.\n" +
                        "\n" +
                        "The Congress shall have Power To lay and collect Taxes, Duties, Imposts and Excises, to pay the Debts and provide for the common Defence and general Welfare of the United States; but all Duties, Imposts and Excises shall be uniform throughout the United States;\n" +
                        "\n" +
                        "To borrow Money on the credit of the United States;\n" +
                        "\n" +
                        "To regulate Commerce with foreign Nations, and among the several States, and with the Indian Tribes;\n" +
                        "\n" +
                        "To establish an uniform Rule of Naturalization, and uniform Laws on the subject of Bankruptcies throughout the United States;\n" +
                        "\n" +
                        "To coin Money, regulate the Value thereof, and of foreign Coin, and fix the Standard of Weights and Measures;\n" +
                        "\n" +
                        "To provide for the Punishment of counterfeiting the Securities and current Coin of the United States;\n" +
                        "\n" +
                        "To establish Post Offices and post Roads;\n" +
                        "\n" +
                        "To promote the Progress of Science and useful Arts, by securing for limited Times to Authors and Inventors the exclusive Right to their respective Writings and Discoveries;\n" +
                        "\n" +
                        "To constitute Tribunals inferior to the supreme Court;\n" +
                        "\n" +
                        "To define and punish Piracies and Felonies committed on the high Seas, and Offences against the Law of Nations;\n" +
                        "\n" +
                        "To declare War, grant Letters of Marque and Reprisal, and make Rules concerning Captures on Land and Water;\n" +
                        "\n" +
                        "To raise and support Armies, but no Appropriation of Money to that Use shall be for a longer Term than two Years;\n" +
                        "\n" +
                        "To provide and maintain a Navy;\n" +
                        "\n" +
                        "To make Rules for the Government and Regulation of the land and naval Forces;\n" +
                        "\n" +
                        "To provide for calling forth the Militia to execute the Laws of the Union, suppress Insurrections and repel Invasions;\n" +
                        "\n" +
                        "To provide for organizing, arming, and disciplining, the Militia, and for governing such Part of them as may be employed in the Service of the United States, reserving to the States respectively, the Appointment of the Officers, and the Authority of training the Militia according to the discipline prescribed by Congress;\n" +
                        "\n" +
                        "To exercise exclusive Legislation in all Cases whatsoever, over such District (not exceeding ten Miles square) as may, by Cession of particular States, and the Acceptance of Congress, become the Seat of the Government of the United States, and to exercise like Authority over all Places purchased by the Consent of the Legislature of the State in which the Same shall be, for the Erection of Forts, Magazines, Arsenals, dock-Yards, and other needful Buildings;—And\n" +
                        "\n" +
                        "To make all Laws which shall be necessary and proper for carrying into Execution the foregoing Powers, and all other Powers vested by this Constitution in the Government of the United States, or in any Department or Officer thereof.\n" +
                        "\n" +
                        "Section. 9.\n" +
                        "\n" +
                        "The Migration or Importation of such Persons as any of the States now existing shall think proper to admit, shall not be prohibited by the Congress prior to the Year one thousand eight hundred and eight, but a Tax or duty may be imposed on such Importation, not exceeding ten dollars for each Person.\n" +
                        "\n" +
                        "The Privilege of the Writ of Habeas Corpus shall not be suspended, unless when in Cases of Rebellion or Invasion the public Safety may require it.\n" +
                        "\n" +
                        "No Bill of Attainder or ex post facto Law shall be passed.\n" +
                        "\n" +
                        "No Capitation, or other direct, Tax shall be laid, unless in Proportion to the Census or enumeration herein before directed to be taken.\n" +
                        "\n" +
                        "No Tax or Duty shall be laid on Articles exported from any State.\n" +
                        "\n" +
                        "No Preference shall be given by any Regulation of Commerce or Revenue to the Ports of one State over those of another: nor shall Vessels bound to, or from, one State, be obliged to enter, clear, or pay Duties in another.\n" +
                        "\n" +
                        "No Money shall be drawn from the Treasury, but in Consequence of Appropriations made by Law; and a regular Statement and Account of the Receipts and Expenditures of all public Money shall be published from time to time.\n" +
                        "\n" +
                        "No Title of Nobility shall be granted by the United States: And no Person holding any Office of Profit or Trust under them, shall, without the Consent of the Congress, accept of any present, Emolument, Office, or Title, of any kind whatever, from any King, Prince, or foreign State.\n" +
                        "\n" +
                        "Section. 10.\n" +
                        "\n" +
                        "No State shall enter into any Treaty, Alliance, or Confederation; grant Letters of Marque and Reprisal; coin Money; emit Bills of Credit; make any Thing but gold and silver Coin a Tender in Payment of Debts; pass any Bill of Attainder, ex post facto Law, or Law impairing the Obligation of Contracts, or grant any Title of Nobility.\n" +
                        "\n" +
                        "No State shall, without the Consent of the Congress, lay any Imposts or Duties on Imports or Exports, except what may be absolutely necessary for executing it's inspection Laws: and the net Produce of all Duties and Imposts, laid by any State on Imports or Exports, shall be for the Use of the Treasury of the United States; and all such Laws shall be subject to the Revision and Controul of the Congress.\n" +
                        "\n" +
                        "No State shall, without the Consent of Congress, lay any Duty of Tonnage, keep Troops, or Ships of War in time of Peace, enter into any Agreement or Compact with another State, or with a foreign Power, or engage in War, unless actually invaded, or in such imminent Danger as will not admit of delay.\n" +
                        "\n" +
                        "Article. II.\n" +
                        "\n" +
                        "Section. 1.\n" +
                        "\n" +
                        "The executive Power shall be vested in a President of the United States of America. He shall hold his Office during the Term of four Years, and, together with the Vice President, chosen for the same Term, be elected, as follows\n" +
                        "\n" +
                        "Each State shall appoint, in such Manner as the Legislature thereof may direct, a Number of Electors, equal to the whole Number of Senators and Representatives to which the State may be entitled in the Congress: but no Senator or Representative, or Person holding an Office of Trust or Profit under the United States, shall be appointed an Elector.\n" +
                        "\n" +
                        "The Electors shall meet in their respective States, and vote by Ballot for two Persons, of whom one at least shall not be an Inhabitant of the same State with themselves. And they shall make a List of all the Persons voted for, and of the Number of Votes for each; which List they shall sign and certify, and transmit sealed to the Seat of the Government of the United States, directed to the President of the Senate. The President of the Senate shall, in the Presence of the Senate and House of Representatives, open all the Certificates, and the Votes shall then be counted. The Person having the greatest Number of Votes shall be the President, if such Number be a Majority of the whole Number of Electors appointed; and if there be more than one who have such Majority, and have an equal Number of Votes, then the House of Representatives shall immediately chuse by Ballot one of them for President; and if no Person have a Majority, then from the five highest on the List the said House shall in like Manner chuse the President. But in chusing the President, the Votes shall be taken by States, the Representation from each State having one Vote; A quorum for this Purpose shall consist of a Member or Members from two thirds of the States, and a Majority of all the States shall be necessary to a Choice. In every Case, after the Choice of the President, the Person having the greatest Number of Votes of the Electors shall be the Vice President. But if there should remain two or more who have equal Votes, the Senate shall chuse from them by Ballot the Vice President.\n" +
                        "\n" +
                        "The Congress may determine the Time of chusing the Electors, and the Day on which they shall give their Votes; which Day shall be the same throughout the United States.\n" +
                        "\n" +
                        "No Person except a natural born Citizen, or a Citizen of the United States, at the time of the Adoption of this Constitution, shall be eligible to the Office of President; neither shall any Person be eligible to that Office who shall not have attained to the Age of thirty five Years, and been fourteen Years a Resident within the United States.\n" +
                        "\n" +
                        "In Case of the Removal of the President from Office, or of his Death, Resignation, or Inability to discharge the Powers and Duties of the said Office, the Same shall devolve on the Vice President, and the Congress may by Law provide for the Case of Removal, Death, Resignation or Inability, both of the President and Vice President, declaring what Officer shall then act as President, and such Officer shall act accordingly, until the Disability be removed, or a President shall be elected.\n" +
                        "\n" +
                        "The President shall, at stated Times, receive for his Services, a Compensation, which shall neither be encreased nor diminished during the Period for which he shall have been elected, and he shall not receive within that Period any other Emolument from the United States, or any of them.\n" +
                        "\n" +
                        "Before he enter on the Execution of his Office, he shall take the following Oath or Affirmation:—\"I do solemnly swear (or affirm) that I will faithfully execute the Office of President of the United States, and will to the best of my Ability, preserve, protect and defend the Constitution of the United States.\"\n" +
                        "\n" +
                        "Section. 2.\n" +
                        "\n" +
                        "The President shall be Commander in Chief of the Army and Navy of the United States, and of the Militia of the several States, when called into the actual Service of the United States; he may require the Opinion, in writing, of the principal Officer in each of the executive Departments, upon any Subject relating to the Duties of their respective Offices, and he shall have Power to grant Reprieves and Pardons for Offences against the United States, except in Cases of Impeachment.\n" +
                        "\n" +
                        "He shall have Power, by and with the Advice and Consent of the Senate, to make Treaties, provided two thirds of the Senators present concur; and he shall nominate, and by and with the Advice and Consent of the Senate, shall appoint Ambassadors, other public Ministers and Consuls, Judges of the supreme Court, and all other Officers of the United States, whose Appointments are not herein otherwise provided for, and which shall be established by Law: but the Congress may by Law vest the Appointment of such inferior Officers, as they think proper, in the President alone, in the Courts of Law, or in the Heads of Departments.\n" +
                        "\n" +
                        "The President shall have Power to fill up all Vacancies that may happen during the Recess of the Senate, by granting Commissions which shall expire at the End of their next Session.\n" +
                        "\n" +
                        "Section. 3.\n" +
                        "\n" +
                        "He shall from time to time give to the Congress Information of the State of the Union, and recommend to their Consideration such Measures as he shall judge necessary and expedient; he may, on extraordinary Occasions, convene both Houses, or either of them, and in Case of Disagreement between them, with Respect to the Time of Adjournment, he may adjourn them to such Time as he shall think proper; he shall receive Ambassadors and other public Ministers; he shall take Care that the Laws be faithfully executed, and shall Commission all the Officers of the United States.\n" +
                        "\n" +
                        "Section. 4.\n" +
                        "\n" +
                        "The President, Vice President and all civil Officers of the United States, shall be removed from Office on Impeachment for, and Conviction of, Treason, Bribery, or other high Crimes and Misdemeanors.\n" +
                        "\n" +
                        "Article. III.\n" +
                        "\n" +
                        "Section. 1.\n" +
                        "\n" +
                        "The judicial Power of the United States, shall be vested in one supreme Court, and in such inferior Courts as the Congress may from time to time ordain and establish. The Judges, both of the supreme and inferior Courts, shall hold their Offices during good Behaviour, and shall, at stated Times, receive for their Services, a Compensation, which shall not be diminished during their Continuance in Office.\n" +
                        "\n" +
                        "Section. 2.\n" +
                        "\n" +
                        "The judicial Power shall extend to all Cases, in Law and Equity, arising under this Constitution, the Laws of the United States, and Treaties made, or which shall be made, under their Authority;—to all Cases affecting Ambassadors, other public Ministers and Consuls;—to all Cases of admiralty and maritime Jurisdiction;—to Controversies to which the United States shall be a Party;—to Controversies between two or more States;— between a State and Citizens of another State,—between Citizens of different States,—between Citizens of the same State claiming Lands under Grants of different States, and between a State, or the Citizens thereof, and foreign States, Citizens or Subjects.\n" +
                        "\n" +
                        "In all Cases affecting Ambassadors, other public Ministers and Consuls, and those in which a State shall be Party, the supreme Court shall have original Jurisdiction. In all the other Cases before mentioned, the supreme Court shall have appellate Jurisdiction, both as to Law and Fact, with such Exceptions, and under such Regulations as the Congress shall make.\n" +
                        "\n" +
                        "The Trial of all Crimes, except in Cases of Impeachment, shall be by Jury; and such Trial shall be held in the State where the said Crimes shall have been committed; but when not committed within any State, the Trial shall be at such Place or Places as the Congress may by Law have directed.\n" +
                        "\n" +
                        "Section. 3.\n" +
                        "\n" +
                        "Treason against the United States, shall consist only in levying War against them, or in adhering to their Enemies, giving them Aid and Comfort. No Person shall be convicted of Treason unless on the Testimony of two Witnesses to the same overt Act, or on Confession in open Court.\n" +
                        "\n" +
                        "The Congress shall have Power to declare the Punishment of Treason, but no Attainder of Treason shall work Corruption of Blood, or Forfeiture except during the Life of the Person attainted.\n" +
                        "\n" +
                        "Article. IV.\n" +
                        "\n" +
                        "Section. 1.\n" +
                        "\n" +
                        "Full Faith and Credit shall be given in each State to the public Acts, Records, and judicial Proceedings of every other State. And the Congress may by general Laws prescribe the Manner in which such Acts, Records and Proceedings shall be proved, and the Effect thereof.\n" +
                        "\n" +
                        "Section. 2.\n" +
                        "\n" +
                        "The Citizens of each State shall be entitled to all Privileges and Immunities of Citizens in the several States.\n" +
                        "\n" +
                        "A Person charged in any State with Treason, Felony, or other Crime, who shall flee from Justice, and be found in another State, shall on Demand of the executive Authority of the State from which he fled, be delivered up, to be removed to the State having Jurisdiction of the Crime.\n" +
                        "\n" +
                        "No Person held to Service or Labour in one State, under the Laws thereof, escaping into another, shall, in Consequence of any Law or Regulation therein, be discharged from such Service or Labour, but shall be delivered up on Claim of the Party to whom such Service or Labour may be due.\n" +
                        "\n" +
                        "Section. 3.\n" +
                        "\n" +
                        "New States may be admitted by the Congress into this Union; but no new State shall be formed or erected within the Jurisdiction of any other State; nor any State be formed by the Junction of two or more States, or Parts of States, without the Consent of the Legislatures of the States concerned as well as of the Congress.\n" +
                        "\n" +
                        "The Congress shall have Power to dispose of and make all needful Rules and Regulations respecting the Territory or other Property belonging to the United States; and nothing in this Constitution shall be so construed as to Prejudice any Claims of the United States, or of any particular State.\n" +
                        "\n" +
                        "Section. 4.\n" +
                        "\n" +
                        "The United States shall guarantee to every State in this Union a Republican Form of Government, and shall protect each of them against Invasion; and on Application of the Legislature, or of the Executive (when the Legislature cannot be convened) against domestic Violence.\n" +
                        "\n" +
                        "Article. V.\n" +
                        "\n" +
                        "The Congress, whenever two thirds of both Houses shall deem it necessary, shall propose Amendments to this Constitution, or, on the Application of the Legislatures of two thirds of the several States, shall call a Convention for proposing Amendments, which, in either Case, shall be valid to all Intents and Purposes, as Part of this Constitution, when ratified by the Legislatures of three fourths of the several States, or by Conventions in three fourths thereof, as the one or the other Mode of Ratification may be proposed by the Congress; Provided that no Amendment which may be made prior to the Year One thousand eight hundred and eight shall in any Manner affect the first and fourth Clauses in the Ninth Section of the first Article; and that no State, without its Consent, shall be deprived of its equal Suffrage in the Senate.\n" +
                        "\n" +
                        "Article. VI.\n" +
                        "\n" +
                        "All Debts contracted and Engagements entered into, before the Adoption of this Constitution, shall be as valid against the United States under this Constitution, as under the Confederation.\n" +
                        "\n" +
                        "This Constitution, and the Laws of the United States which shall be made in Pursuance thereof; and all Treaties made, or which shall be made, under the Authority of the United States, shall be the supreme Law of the Land; and the Judges in every State shall be bound thereby, any Thing in the Constitution or Laws of any State to the Contrary notwithstanding.\n" +
                        "\n" +
                        "The Senators and Representatives before mentioned, and the Members of the several State Legislatures, and all executive and judicial Officers, both of the United States and of the several States, shall be bound by Oath or Affirmation, to support this Constitution; but no religious Test shall ever be required as a Qualification to any Office or public Trust under the United States.\n" +
                        "\n" +
                        "Article. VII.\n" +
                        "\n" +
                        "The Ratification of the Conventions of nine States, shall be sufficient for the Establishment of this Constitution between the States so ratifying the Same.\n" +
                        "\n" +
                        "The Word, \"the,\" being interlined between the seventh and eighth Lines of the first Page, The Word \"Thirty\" being partly written on an Erazure in the fifteenth Line of the first Page, The Words \"is tried\" being interlined between the thirty second and thirty third Lines of the first Page and the Word \"the\" being interlined between the forty third and forty fourth Lines of the second Page.\n" +
                        "\n" +
                        "Attest William Jackson Secretary\n" +
                        "\n" +
                        "done in Convention by the Unanimous Consent of the States present the Seventeenth Day of September in the Year of our Lord one thousand seven hundred and Eighty seven and of the Independance of the United States of America the Twelfth In witness whereof We have hereunto subscribed our Names"
            ),


            TextForReading(
                "The Rainbow Passage",
                "When the sunlight strikes raindrops in the air, they act as a prism and form a rainbow. The rainbow is a division of white light into many beautiful colors. These take the shape of a long round arch, with its path high above, and its two ends apparently beyond the horizon. There is, according to legend, a boiling pot of gold at one end. People look, but no one ever finds it. When a man looks for something beyond his reach, his friends say he is looking for the pot of gold at the end of the rainbow. Throughout the centuries people have explained the rainbow in various ways. Some have accepted it as a miracle without physical explanation. To the Hebrews it was a token that there would be no more universal floods. The Greeks used to imagine that it was a sign from the gods to foretell war or heavy rain. The Norsemen considered the rainbow as a bridge over which the gods passed from earth to their home in the sky. Others have tried to explain the phenomenon physically. Aristotle thought that the rainbow was caused by reflection of the sun's rays by the rain. Since then physicists have found that it is not reflection, but refraction by the raindrops which causes the rainbows. Many complicated ideas about the rainbow have been formed. The difference in the rainbow depends considerably upon the size of the drops, and the width of the colored band increases as the size of the drops increases. The actual primary rainbow observed is said to be the effect of super-imposition of a number of bows. If the red of the second bow falls upon the green of the first, the result is to give a bow with an abnormally wide yellow band, since red and green light when mixed form yellow. This is a very common type of bow, one showing mainly red and yellow, with little or no green or blue.\n" +
                        "\n" +
                        "From Fairbanks, G. (1960). Voice and articulation drillbook, 2nd edn. New York: Harper & Row. pp124-139."
            ),


            TextForReading(
                "الإعلان العالمي لحقوق الإنسان",
                "لديباجة\n" +
                        "\n" +
                        "لما كان الاعتراف بالكرامة المتأصلة في جميع أعضاء الأسرة البشرية وبحقوقهم المتساوية الثابتة هو أساس الحرية والعدل والسلام في العالم،\n" +
                        "\n" +
                        "في حين أن تناسي حقوق الإنسان قد أدى إلى أعمال همجية أثارت غضب ضمير البشرية، وظهور عالم يتمتع فيه البشر بحرية الكلام والمعتقد والتحرر من الخوف والعوز قد أعلن أنه أعلى تطلعات من عامة الناس،\n" +
                        "\n" +
                        "ولما كان من الضروري أن يتولى القانون حماية حقوق الإنسان لكيلا يضطر المرء آخر الأمر إلى التمرد على الاستبداد والظلم،\n" +
                        "\n" +
                        "ولما كان من الجوهري تعزيز تنمية العلاقات الودية بين الدول،\n" +
                        "\n" +
                        "ولما كانت شعوب الأمم المتحدة قد أكدت في الميثاق من جديد إيمانها بحقوق الإنسان الأساسية وبكرامة الفرد وقدره وبما للرجال والنساء من حقوق متساوية وحزمت أمرها على أن تدفع بالرقي الاجتماعي قدماً وأن ترفع مستوى الحياة في جو من الحرية أفسح،\n" +
                        "\n" +
                        "ولما كانت الدول الأعضاء قد تعهدت بالتعاون مع الأمم المتحدة على ضمان اطراد مراعاة حقوق الإنسان والحريات الأساسية واحترامها،\n" +
                        "\n" +
                        "ولما كان للإدراك العام لهذه الحقوق والحريات الأهمية الكبرى للوفاء التام بهذا التعهد،\n" +
                        "\n" +
                        "فالآن،\n" +
                        "\n" +
                        "الجمعية العامة،\n" +
                        "\n" +
                        "تنادي بهذا الإعلان العالمي لحقوق الإنسان على أنه المستوى المشترك الذي ينبغي أن تستهدفه كافة الشعوب والأمم حتى يسعى كل فرد وهيئة في المجتمع، واضعين على الدوام هذا الإعلان نصب أعينهم، إلى توطيد احترام هذه الحقوق والحريات عن طريق التعليم والتربية واتخاذ إجراءات مطردة، قومية وعالمية، لضمان الاعتراف بها ومراعاتها بصورة عالمية فعالة بين الدول الأعضاء ذاتها وشعوب البقاع الخاضعة لسلطاتها.\n" +
                        "\n" +
                        "المادة 1\n" +
                        "\n" +
                        "يولد جميع الناس أحراراً ومتساوين في الكرامة والحقوق. وهم قد وهبوا العقل والوجدان وعليهم أن يعاملوا بعضهم بعضاً بروح الإخاء.\n" +
                        "\n" +
                        "المادة 2\n" +
                        "\n" +
                        "لكلِّ إنسان حقُّ التمتُّع بجميع الحقوق والحرِّيات المذكورة في هذا الإعلان، دونما تمييز من أيِّ نوع، ولا سيما التمييز بسبب العنصر، أو اللون، أو الجنس، أو اللغة، أو الدِّين، أو الرأي سياسيًّا وغير سياسي، أو الأصل الوطني أو الاجتماعي، أو الثروة، أو المولد، أو أيِّ وضع آخر. وفضلاً عن ذلك لا يجوز التمييزُ علي أساس الوضع السياسي أو القانوني أو الدولي للبلد أو الإقليم الذي ينتمي إليه الشخص، سواء أكان مستقلاًّ أو موضوعًا تحت الوصاية أو غير متمتِّع بالحكم الذاتي أم خاضعًا لأيِّ قيد آخر على سيادته.\n" +
                        "\n" +
                        "المادة 3\n" +
                        "\n" +
                        "لكلِّ فرد الحقُّ في الحياة والحرِّية وفي الأمان على شخصه.\n" +
                        "\n" +
                        "المادة 4\n" +
                        "\n" +
                        "لا يجوز استرقاقُ أحد أو استعبادُه، ويُحظر الرق والاتجار بالرقيق بجميع صورهما.\n" +
                        "\n" +
                        "المادة 5\n" +
                        "\n" +
                        "لا يجوز إخضاعُ أحد للتعذيب ولا للمعاملة أو العقوبة القاسية أو اللاإنسانية أو الحاطَّة بالكرامة.\n" +
                        "\n" +
                        "المادة 6\n" +
                        "\n" +
                        "لكلِّ إنسان، في كلِّ مكان، الحقُّ بأن يُعترَف له بالشخصية القانونية.\n" +
                        "\n" +
                        "المادة 7\n" +
                        "\n" +
                        "الناسُ جميعًا سواءٌ أمام القانون، وهم يتساوون في حقِّ التمتُّع بحماية القانون دونما تمييز، كما يتساوون في حقِّ التمتُّع بالحماية من أيِّ تمييز ينتهك هذا الإعلانَ ومن أيِّ تحريض على مثل هذا التمييز.\n" +
                        "\n" +
                        "المادة 8\n" +
                        "\n" +
                        "لكلِّ شخص حقُّ اللجوء إلى المحاكم الوطنية المختصَّة لإنصافه الفعلي من أيَّة أعمال تَنتهك الحقوقَ الأساسيةَ التي يمنحها إيَّاه الدستورُ أو القانونُ.\n" +
                        "\n" +
                        "المادة 9\n" +
                        "\n" +
                        "لا يجوز اعتقالُ أيِّ إنسان أو حجزُه أو نفيُه تعسُّفًا.\n" +
                        "\n" +
                        "المادة 10\n" +
                        "\n" +
                        "لكلِّ إنسان، على قدم المساواة التامة مع الآخرين، الحقُّ في أن تَنظر قضيتَه محكمةٌ مستقلَّةٌ ومحايدةٌ، نظرًا مُنصفًا وعلنيًّا، للفصل في حقوقه والتزاماته وفى أيَّة تهمة جزائية تُوجَّه إليه.\n" +
                        "\n" +
                        "المادة 11\n" +
                        "\n" +
                        "1. كلُّ شخص متَّهم بجريمة يُعتبَر بريئًا إلى أن يثبت ارتكابُه لها قانونًا في محاكمة علنية تكون قد وُفِّرت له فيها جميعُ الضمانات اللازمة للدفاع عن نفسه.\n" +
                        "2.لا يُدان أيُّ شخص بجريمة بسبب أيِّ عمل أو امتناع عن عمل لم يكن في حينه يشكِّل جُرمًا بمقتضى القانون الوطني أو الدولي، كما لا تُوقَع عليه أيَّةُ عقوبة أشدَّ من تلك التي كانت ساريةً في الوقت الذي ارتُكب فيه الفعل الجُرمي.\n" +
                        "\n" +
                        "المادة 12\n" +
                        "\n" +
                        "لا يجوز تعريضُ أحد لتدخُّل تعسُّفي في حياته الخاصة أو في شؤون أسرته أو مسكنه أو مراسلاته، ولا لحملات تمسُّ شرفه وسمعته. ولكلِّ شخص حقٌّ في أن يحميه القانونُ من مثل ذلك التدخُّل أو تلك الحملات.\n" +
                        "\n" +
                        "المادة 13\n" +
                        "\n" +
                        "1. لكلِّ فرد حقٌّ في حرِّية التنقُّل وفي اختيار محلِّ إقامته داخل حدود الدولة.\n" +
                        "\n" +
                        "2. لكلِّ فرد حقٌّ في مغادرة أيِّ بلد، بما في ذلك بلده، وفي العودة إلى بلده.\n" +
                        "\n" +
                        "المادة 14\n" +
                        "\n" +
                        "1. لكلِّ فرد حقُّ التماس ملجأ في بلدان أخرى والتمتُّع به خلاصًا من الاضطهاد.\n" +
                        "2. لا يمكن التذرُّعُ بهذا الحقِّ إذا كانت هناك ملاحقةٌ ناشئةٌ بالفعل عن جريمة غير سياسية أو عن أعمال تناقض مقاصدَ الأمم المتحدة ومبادئها.\n" +
                        "\n" +
                        "المادة 15\n" +
                        "\n" +
                        "1. لكل فرد حق التمتع بجنسية ما.\n" +
                        "2. لا يجوز، تعسُّفًا، حرمانُ أيِّ شخص من جنسيته ولا من حقِّه في تغيير جنسيته.\n" +
                        "\n" +
                        "المادة 16\n" +
                        "\n" +
                        "1. للرجل والمرأة، متى أدركا سنَّ البلوغ، حقُّ التزوُّج وتأسيس أسرة، دون أيِّ قيد بسبب العِرق أو الجنسية أو الدِّين. وهما متساويان في الحقوق لدى التزوُّج وخلال قيام الزواج ولدى انحلاله.\n" +
                        "2. ا يُعقَد الزواجُ إلاَّ برضا الطرفين المزمع زواجهما رضاءً كاملً لا إكراهَ فيه.\n" +
                        "3.  الأسرةُ هي الخليةُ الطبيعيةُ والأساسيةُ في المجتمع، ولها حقُّ التمتُّع بحماية المجتمع والدولة.\n" +
                        "\n" +
                        "المادة 17\n" +
                        "\n" +
                        "1. لكلِّ فرد حقٌّ في التملُّك، بمفرده أو بالاشتراك مع غيره.\n" +
                        "2. لا يجوز تجريدُ أحدٍ من مُلكه تعسُّفًا.\n" +
                        "\n" +
                        "المادة 18\n" +
                        "\n" +
                        "لكلِّ شخص حقٌّ في حرِّية الفكر والوجدان والدِّين، ويشمل هذا الحقُّ حرِّيته في تغيير دينه أو معتقده، وحرِّيته في إظهار دينه أو معتقده بالتعبُّد وإقامة الشعائر والممارسة والتعليم، بمفرده أو مع جماعة، وأمام الملأ أو على حدة.\n" +
                        "\n" +
                        "المادة 19\n" +
                        "\n" +
                        "لكلِّ شخص حقُّ التمتُّع بحرِّية الرأي والتعبير، ويشمل هذا الحقُّ حرِّيته في اعتناق الآراء دون مضايقة، وفي التماس الأنباء والأفكار وتلقِّيها ونقلها إلى الآخرين، بأيَّة وسيلة ودونما اعتبار للحدود.\n" +
                        "\n" +
                        "المادة 20\n" +
                        "\n" +
                        "1. لكلِّ شخص حقٌّ في حرِّية الاشتراك في الاجتماعات والجمعيات السلمية.\n" +
                        "2.  لا يجوز إرغامُ أحدٍ على الانتماء إلى جمعية ما.\n" +
                        "\n" +
                        "المادة 21\n" +
                        "\n" +
                        "1.  لكلِّ شخص حقُّ المشاركة في إدارة الشؤون العامة لبلده، إمَّا مباشرةً وإمَّا بواسطة ممثِّلين يُختارون في حرِّية.\n" +
                        "2. لكلِّ شخص، بالتساوي مع الآخرين، حقُّ تقلُّد الوظائف العامَّة في بلده.\n" +
                        "3. إرادةُ الشعب هي مناطُ سلطة الحكم، ويجب أن تتجلىَّ هذه الإرادة من خلال انتخابات نزيهة تجرى دوريًّا بالاقتراع العام وعلى قدم المساواة بين الناخبين وبالتصويت السرِّي أو بإجراء مكافئ من حيث ضمان حرِّية التصويت.\n" +
                        "\n" +
                        "المادة 22\n" +
                        "\n" +
                        "لكلِّ شخص، بوصفه عضوًا في المجتمع، حقٌّ في الضمان الاجتماعي، ومن حقِّه أن تُوفَّر له، من خلال المجهود القومي والتعاون الدولي، وبما يتَّفق مع هيكل كلِّ دولة ومواردها، الحقوقُ الاقتصاديةُ والاجتماعيةُ والثقافيةُ التي لا غنى عنها لكرامته ولتنامي شخصيته في حرِّية.\n" +
                        "\n" +
                        "المادة 23\n" +
                        "\n" +
                        "1. لكلِّ شخص حقُّ العمل، وفي حرِّية اختيار عمله، وفي شروط عمل عادلة ومُرضية، وفي الحماية من البطالة.\n" +
                        "2. لجميع الأفراد، دون أيِّ تمييز، الحقُّ في أجٍر متساوٍ على العمل المتساوي.\n" +
                        "3.  لكلِّ فرد يعمل حقٌّ في مكافأة عادلة ومُرضية تكفل له ولأسرته عيشةً لائقةً بالكرامة البشرية، وتُستكمَل، عند الاقتضاء، بوسائل أخرى للحماية الاجتماعية.\n" +
                        "4. لكلِّ شخص حقُّ إنشاء النقابات مع آخرين والانضمام إليها من أجل حماية مصالحه.\n" +
                        "\n" +
                        "المادة 24\n" +
                        "\n" +
                        "لكلِّ شخص حقٌّ في الراحة وأوقات الفراغ، وخصوصًا في تحديد معقول لساعات العمل وفي إجازات دورية مأجورة.\n" +
                        "\n" +
                        "المادة 25\n" +
                        "\n" +
                        "1. لكلِّ شخص حقٌّ في مستوى معيشة يكفي لضمان الصحة والرفاهة له ولأسرته، وخاصَّةً على صعيد المأكل والملبس والمسكن والعناية الطبية وصعيد الخدمات الاجتماعية الضرورية، وله الحقُّ في ما يأمن به الغوائل في حالات البطالة أو المرض أو العجز أو الترمُّل أو الشيخوخة أو غير ذلك من الظروف الخارجة عن إرادته والتي تفقده أسباب عيشه.\n" +
                        "2.للأمومة والطفولة حقٌّ في رعاية ومساعدة خاصَّتين. ولجميع الأطفال حقُّ التمتُّع بذات الحماية الاجتماعية سواء وُلِدوا في إطار الزواج أو خارج هذا الإطار.\n" +
                        "\n" +
                        "المادة 26\n" +
                        "\n" +
                        "1. كلِّ شخص حقٌّ في التعليم. ويجب أن يُوفَّر التعليمُ مجَّانًا، على الأقل في مرحلتيه الابتدائية والأساسية. ويكون التعليمُ الابتدائيُّ إلزاميًّا. ويكون التعليمُ الفنِّي والمهني متاحًا للعموم. ويكون التعليمُ العالي مُتاحًا للجميع تبعًا لكفاءتهم.\n" +
                        "2. يجب أن يستهدف التعليمُ التنميةَ الكاملةَ لشخصية الإنسان وتعزيز احترام حقوق الإنسان والحريات الأساسية. كما يجب أن يعزِّز التفاهمَ والتسامحَ والصداقةَ بين جميع الأمم وجميع الفئات العنصرية أو الدينية، وأن يؤيِّد الأنشطةَ التي تضطلع بها الأممُ المتحدةُ لحفظ السلام.\n" +
                        "3. للآباء، على سبيل الأولوية، حقُّ اختيار نوع التعليم الذي يُعطى لأولادهم.\n" +
                        "\n" +
                        "المادة 27\n" +
                        "\n" +
                        "1. لكلِّ شخص حقُّ المشاركة الحرَّة في حياة المجتمع الثقافية، وفي الاستمتاع بالفنون، والإسهام في التقدُّم العلمي وفي الفوائد التي تنجم عنه.\n" +
                        "2. لكلِّ شخص حقٌّ في حماية المصالح المعنوية والمادية المترتِّبة على أيِّ إنتاج علمي أو أدبي أو فنِّي من صنعه .\n" +
                        "\n" +
                        "المادة 28\n" +
                        "\n" +
                        "لكل فرد الحق في نظام اجتماعي ودولي يمكن أن تتحقق فيه الحقوق والحريات المنصوص عليها في هذا الإعلان بالكامل.\n" +
                        "\n" +
                        "المادة 29\n" +
                        "\n" +
                        "1. على كلِّ فرد واجباتٌ إزاء الجماعة، التي فيها وحدها يمكن أن تنمو شخصيته النمو الحر الكامل.\n" +
                        "2. لا يُخضع أيُّ فرد، في ممارسة حقوقه وحرِّياته، إلاَّ للقيود التي يقرِّرها القانونُ مستهدفًا منها، حصرًا، ضمانَ الاعتراف الواجب بحقوق وحرِّيات الآخرين واحترامها، والوفاءَ بالعادل من مقتضيات الفضيلة والنظام العام ورفاه الجميع في مجتمع ديمقراطي.\n" +
                        "3.  لا يجوز في أيِّ حال أن تُمارَس هذه الحقوقُ على نحو يناقض مقاصدَ الأمم المتحدة ومبادئها.\n" +
                        "\n" +
                        "المادة 30\n" +
                        "\n" +
                        "ليس في هذا الإعلان أيُّ نصٍّ يجوز تأويله على نحو يفيد انطواءه على تخويل أيَّة دولة أو جماعة، أو أيِّ فرد، أيَّ حقٍّ في القيام بأيِّ نشاط أو بأيِّ فعل يهدف إلى هدم أيٍّ من الحقوق والحرِّيات المنصوص عليها فيه."
            ),


            TextForReading(
                "世界人权宣言",
                "序言\n" +
                        "\n" +
                        "　　鉴于对人类家庭所有成员的固有尊严及其平等的和不移的权利的承认，乃是世界自由、正义与和平的基础，\n" +
                        "\n" +
                        "　　鉴于对人权的无视和侮蔑已发展为野蛮暴行，这些暴行玷污了人类的良心，而一个人人享有言论和信仰自由并免予恐惧和匮乏的世界的来临，已被宣布为普通人民的最高愿望，\n" +
                        "\n" +
                        "　　鉴于为使人类不致迫不得已铤而走险对暴政和压迫进行反叛，有必要使人权受法治的保护，\n" +
                        "\n" +
                        "　　鉴于有必要促进各国间友好关系的发展，\n" +
                        "\n" +
                        "　　鉴于各联合国国家的人民已在联合国宪章中重申他们对基本人权、人格尊严和价值以及男女平等权利的信念，并决心促成较大自由中的社会进步和生活水平的改善，\n" +
                        "\n" +
                        "　　鉴于各会员国业已誓愿同联合国合作以促进对人权和基本自由的普遍尊重和遵行，\n" +
                        "\n" +
                        "　　鉴于对这些权利和自由的普遍了解对于这个誓愿的充分实现具有很大的重要性，\n" +
                        "\n" +
                        "　　因此现在，\n" +
                        "\n" +
                        "　　大会，\n" +
                        "\n" +
                        "　　发布这一世界人权宣言，作为所有人民和所有国家努力实现的共同标准，以期每一个人和社会机构经常铭念本宣言，努力通过教诲和教育促进对权利和自由的尊重，并通过国家的和国际的渐进措施，使这些权利和自由在各会员国本身人民及在其管辖下领土的人民中得到普遍和有效的承认和遵行；\n" +
                        "\n" +
                        "第一条\n" +
                        "\n" +
                        "　　人人生而自由，在尊严和权利上一律平等。他们赋有理性和良心，并应以兄弟关系的精神相对待。\n" +
                        "\n" +
                        "第二条\n" +
                        "\n" +
                        "　　人人有资格享有本宣言所载的一切权利和自由，不分种族、肤色、性别、语言、宗教、政治或其他见解、国籍或社会出身、财产、出生或其他身分等任何区别。 并且不得因一人所属的国家或领土的政治的、行政的或者国际的地位之不同而有所区别，无论该领土是独立领土、托管领土、非自治领土或者处于其他任何主权受限制的情况之下。\n" +
                        "\n" +
                        "第三条\n" +
                        "\n" +
                        "　　人人有权享有生命、自由和人身安全。\n" +
                        "\n" +
                        "第四条\n" +
                        "\n" +
                        "　　任何人不得使为奴隶或奴役；一切形式的奴隶制度和奴隶买卖，均应予以禁止。\n" +
                        "\n" +
                        "第五条\n" +
                        "\n" +
                        "　　任何人不得加以酷刑，或施以残忍的、不人道的或侮辱性的待遇或刑罚。\n" +
                        "\n" +
                        "第六条\n" +
                        "\n" +
                        "　　人人在任何地方有权被承认在法律前的人格。\n" +
                        "\n" +
                        "第七条\n" +
                        "\n" +
                        "　　法律之前人人平等，并有权享受法律的平等保护，不受任何歧视。人人有权享受平等保护，以免受违反本宣言的任何歧视行为以及煽动这种歧视的任何行为之害。\n" +
                        "\n" +
                        "第八条\n" +
                        "\n" +
                        "　　任何人当宪法或法律所赋予他的基本权利遭受侵害时，有权由合格的国家法庭对这种侵害行为作有效的补救。\n" +
                        "\n" +
                        "第九条\n" +
                        "\n" +
                        "　　任何人不得加以任意逮捕、拘禁或放逐。\n" +
                        "\n" +
                        "第十条\n" +
                        "\n" +
                        "　　人人完全平等地有权由一个独立而无偏倚的法庭进行公正的和公开的审讯，以确定他的权利和义务并判定对他提出的任何刑事指控。\n" +
                        "\n" +
                        "第十一条\n" +
                        "\n" +
                        "　　㈠ 凡受刑事控告者，在未经获得辩护上所需的一切保证的公开审判而依法证实有罪以前，有权被视为无罪。\n" +
                        "　　㈡ 任何人的任何行为或不行为，在其发生时依国家法或国际法均不构成刑事罪者，不得被判为犯有刑事罪。刑罚不得重于犯罪时适用的法律规定。\n" +
                        "\n" +
                        "第十二条\n" +
                        "\n" +
                        "　　任何人的私生活、家庭、住宅和通信不得任意干涉，他的荣誉和名誉不得加以攻击。人人有权享受法律保护，以免受这种干涉或攻击。\n" +
                        "\n" +
                        "第十三条\n" +
                        "\n" +
                        "　　㈠ 人人在各国境内有权自由迁徙和居住。\n" +
                        "　　㈡ 人人有权离开任何国家，包括其本国在内，并有权返回他的国家。\n" +
                        "\n" +
                        "第十四条\n" +
                        "\n" +
                        "　　㈠ 人人有权在其他国家寻求和享受庇护以避免迫害。\n" +
                        "　　㈡ 在真正由于非政治性的罪行或违背联合国的宗旨和原则的行为而被起诉的情况下，不得援用此种权利。\n" +
                        "\n" +
                        "第十五条\n" +
                        "\n" +
                        "　　㈠ 人人有权享有国籍。\n" +
                        "　　㈡ 任何人的国籍不得任意剥夺，亦不得否认其改变国籍的权利。\n" +
                        "\n" +
                        "第十六条\n" +
                        "\n" +
                        "　　㈠ 成年男女，不受种族、国籍或宗教的任何限制有权婚嫁和成立家庭。他们在婚姻方面，在结婚期间和在解除婚约时，应有平等的权利。\n" +
                        "　　㈡ 只有经男女双方的自由和完全的同意，才能缔婚。\n" +
                        "　　㈢ 家庭是天然的和基本的社会单元，并应受社会和国家的保护。\n" +
                        "\n" +
                        "第十七条\n" +
                        "\n" +
                        "　　㈠ 人人得有单独的财产所有权以及同他人合有的所有权。\n" +
                        "　　㈡ 任何人的财产不得任意剥夺。\n" +
                        "\n" +
                        "第十八条\n" +
                        "\n" +
                        "　　人人有思想、良心和宗教自由的权利；此项权利包括改变他的宗教或信仰的自由，以及单独或集体、公开或秘密地以教义、实践、礼拜和戒律表示他的宗教或信仰的自由。\n" +
                        "\n" +
                        "第十九条\n" +
                        "\n" +
                        "　　人人有权享有主张和发表意见的自由；此项权利包括持有主张而不受干涉的自由，和通过任何媒介和不论国界寻求、接受和传递消息和思想的自由。\n" +
                        "\n" +
                        "第二十条\n" +
                        "\n" +
                        "　　㈠ 人人有权享有和平集会和结社的自由。\n" +
                        "　　㈡ 任何人不得迫使隶属于某一团体。\n" +
                        "\n" +
                        "第二十一条\n" +
                        "\n" +
                        "　　㈠ 人人有直接或通过自由选择的代表参与治理本国的权利。\n" +
                        "　　㈡ 人人有平等机会参加本国公务的权利。\n" +
                        "　　㈢ 人民的意志是政府权力的基础；这一意志应以定期的和真正的选举予以表现，而选举应依据普遍和平等的投票权，并以不记名投票或相当的自由投票程序进行。\n" +
                        "\n" +
                        "第二十二条\n" +
                        "\n" +
                        "　　每个人，作为社会的一员，有权享受社会保障，并有权享受他的个人尊严和人格的自由发展所必需的经济、社会和文化方面各种权利的实现，这种实现是通过国家努力和国际合作并依照各国的组织和资源情况。\n" +
                        "\n" +
                        "第二十三条\n" +
                        "\n" +
                        "　　㈠ 人人有权工作、自由选择职业、享受公正和合适的工作条件并享受免于失业的保障。\n" +
                        "　　㈡ 人人有同工同酬的权利，不受任何歧视。\n" +
                        "　　㈢ 每一个工作的人，有权享受公正和合适的报酬，保证使他本人和家属有一个符合人的尊严的生活条件，必要时并辅以其他方式的社会保障。\n" +
                        "　　㈣ 人人有为维护其利益而组织和参加工会的权利。\n" +
                        "\n" +
                        "第二十四条\n" +
                        "\n" +
                        "　　人人有享有休息和闲暇的权利，包括工作时间有合理限制和定期给薪休假的权利。\n" +
                        "\n" +
                        "第二十五条\n" +
                        "\n" +
                        "　　㈠ 人人有权享受为维持他本人和家属的健康和福利所需的生活水准，包括食物、衣着、住房、医疗和必要的社会服务；在遭到失业、疾病、残废、守寡、衰老或在其他不能控制的情况下丧失谋生能力时，有权享受保障。\n" +
                        "　　㈡ 母亲和儿童有权享受特别照顾和协助。一切儿童，无论婚生或非婚生，都应享受同样的社会保护。\n" +
                        "\n" +
                        "第二十六条\n" +
                        "\n" +
                        "　　㈠ 人人都有受教育的权利，教育应当免费，至少在初级和基本阶段应如此。初级教育应属义务性质。技术和职业教育应普遍设立。高等教育应根据成绩而对一切人平等开放。\n" +
                        "　　㈡ 教育的目的在于充分发展人的个性并加强对人权和基本自由的尊重。教育应促进各国、各种族或各宗教集团间的了解、容忍和友谊，并应促进联合国维护和平的各项活动。\n" +
                        "　　㈢ 父母对其子女所应受的教育的种类，有优先选择的权利。\n" +
                        "\n" +
                        "第二十七条\n" +
                        "\n" +
                        "　　㈠ 人人有权自由参加社会的文化生活，享受艺术，并分享科学进步及其产生的福利。\n" +
                        "　　㈡ 人人对由于他所创作的任何科学、文学或美术作品而产生的精神的和物质的利益，有享受保护的权利。\n" +
                        "\n" +
                        "第二十八条\n" +
                        "\n" +
                        "　　人人有权要求一种社会的和国际的秩序，在这种秩序中，本宣言所载的权利和自由能获得充分实现。\n" +
                        "\n" +
                        "第二十九条\n" +
                        "\n" +
                        "　　㈠ 人人对社会负有义务，因为只有在社会中他的个性才可能得到自由和充分的发展。\n" +
                        "　　㈡ 人人在行使他的权利和自由时，只受法律所确定的限制，确定此种限制的唯一目的在于保证对旁人的权利和自由给予应有的承认和尊重，并在一个民主的社会中适应道德、公共秩序和普遍福利的正当需要。\n" +
                        "　　㈢ 这些权利和自由的行使，无论在任何情形下均不得违背联合国的宗旨和原则。\n" +
                        "\n" +
                        "第三十条\n" +
                        "\n" +
                        "　　本宣言的任何条文，不得解释为默许任何国家、集团或个人有权进行任何旨在破坏本宣言所载的任何权利和自由的活动或行为。"
            ),


            TextForReading(
                "Déclaration universelle des droits de l'homme",
                "Préambule\n" +
                        "\n" +
                        "Considérant que la reconnaissance de la dignité inhérente à tous les membres de la famille humaine et de leurs droits égaux et inaliénables constitue le fondement de la liberté, de la justice et de la paix dans le monde.\n" +
                        "\n" +
                        "Considérant que la méconnaissance et le mépris des droits de l'homme ont conduit à des actes de barbarie qui révoltent la conscience de l'humanité et que l'avènement d'un monde où les êtres humains seront libres de parler et de croire, libérés de la terreur et de la misère, a été proclamé comme la plus haute aspiration de l'homme.\n" +
                        "\n" +
                        "Considérant qu'il est essentiel que les droits de l'homme soient protégés par un régime de droit pour que l'homme ne soit pas contraint, en suprême recours, à la révolte contre la tyrannie et l'oppression.\n" +
                        "\n" +
                        "Considérant qu'il est essentiel d'encourager le développement de relations amicales entre nations.\n" +
                        "\n" +
                        "Considérant que dans la Charte les peuples des Nations Unies ont proclamé à nouveau leur foi dans les droits fondamentaux de l'homme, dans la dignité et la valeur de la personne humaine, dans l'égalité des droits des hommes et des femmes, et qu'ils se sont déclarés résolus à favoriser le progrès social et à instaurer de meilleures conditions de vie dans une liberté plus grande.\n" +
                        "\n" +
                        "Considérant que les Etats Membres se sont engagés à assurer, en coopération avec l'Organisation des Nations Unies, le respect universel et effectif des droits de l'homme et des libertés fondamentales.\n" +
                        "\n" +
                        "Considérant qu'une conception commune de ces droits et libertés est de la plus haute importance pour remplir pleinement cet engagement.\n" +
                        "\n" +
                        "L'Assemblée générale proclame la présente Déclaration universelle des droits de l'homme comme l'idéal commun à atteindre par tous les peuples et toutes les nations afin que tous les individus et tous les organes de la société, ayant cette Déclaration constamment à l'esprit, s'efforcent, par l'enseignement et l'éducation, de développer le respect de ces droits et libertés et d'en assurer, par des mesures progressives d'ordre national et international, la reconnaissance et l'application universelles et effectives, tant parmi les populations des Etats Membres eux-mêmes que parmi celles des territoires placés sous leur juridiction.\n" +
                        "\n" +
                        "Article premier\n" +
                        "\n" +
                        "Tous les êtres humains naissent libres et égaux en dignité et en droits. Ils sont doués de raison et de conscience et doivent agir les uns envers les autres dans un esprit de fraternité.\n" +
                        "\n" +
                        "Article 2\n" +
                        "\n" +
                        "1. Chacun peut se prévaloir de tous les droits et de toutes les libertés proclamés dans la présente Déclaration, sans distinction aucune, notamment de race, de couleur, de sexe, de langue, de religion, d'opinion politique ou de toute autre opinion, d'origine nationale ou sociale, de fortune, de naissance ou de toute autre situation.\n" +
                        "2. De plus, il ne sera fait aucune distinction fondée sur le statut politique, juridique ou international du pays ou du territoire dont une personne est ressortissante, que ce pays ou territoire soit indépendant, sous tutelle, non autonome ou soumis à une limitation quelconque de souveraineté.\n" +
                        "\n" +
                        "Article 3\n" +
                        "\n" +
                        "Tout individu a droit à la vie, à la liberté et à la sûreté de sa personne.\n" +
                        "\n" +
                        "Article 4\n" +
                        "\n" +
                        "Nul ne sera tenu en esclavage ni en servitude; l'esclavage et la traite des esclaves sont interdits sous toutes leurs formes.\n" +
                        "\n" +
                        "Article 5\n" +
                        "\n" +
                        "Nul ne sera soumis à la torture, ni à des peines ou traitements cruels, inhumains ou dégradants.\n" +
                        "\n" +
                        "Article 6\n" +
                        "\n" +
                        "Chacun a le droit à la reconnaissance en tous lieux de sa personnalité juridique.\n" +
                        "\n" +
                        "Article 7\n" +
                        "\n" +
                        "Tous sont égaux devant la loi et ont droit sans distinction à une égale protection de la loi. Tous ont droit à une protection égale contre toute discrimination qui violerait la présente Déclaration et contre toute provocation à une telle discrimination.\n" +
                        "\n" +
                        "Article 8\n" +
                        "\n" +
                        "Toute personne a droit à un recours effectif devant les juridictions nationales compétentes contre les actes violant les droits fondamentaux qui lui sont reconnus par la constitution ou par la loi.\n" +
                        "\n" +
                        "Article 9\n" +
                        "\n" +
                        "Nul ne peut être arbitrairement arrêté, détenu ou exilé.\n" +
                        "\n" +
                        "Article 10\n" +
                        "\n" +
                        "Toute personne a droit, en pleine égalité, à ce que sa cause soit entendue équitablement et publiquement par un tribunal indépendant et impartial, qui décidera, soit de ses droits et obligations, soit du bien-fondé de toute accusation en matière pénale dirigée contre elle.\n" +
                        "\n" +
                        "Article 11\n" +
                        "\n" +
                        "1. Toute personne accusée d'un acte délictueux est présumée innocente jusqu'à ce que sa culpabilité ait été légalement établie au cours d'un procès public où toutes les garanties nécessaires à sa défense lui auront été assurées. \n" +
                        "\n" +
                        "2. Nul ne sera condamné pour des actions ou omissions qui, au moment où elles ont été commises, ne constituaient pas un acte délictueux d'après le droit national ou international. De même, il ne sera infligé aucune peine plus forte que celle qui était applicable au moment où l'acte délictueux a été commis.\n" +
                        "\n" +
                        "Article 12\n" +
                        "\n" +
                        "Nul ne sera l'objet d'immixtions arbitraires dans sa vie privée, sa famille, son domicile ou sa correspondance, ni d'atteintes à son honneur et à sa réputation. Toute personne a droit à la protection de la loi contre de telles immixtions ou de telles atteintes.\n" +
                        "\n" +
                        "Article 13\n" +
                        "\n" +
                        "1. Toute personne a le droit de circuler librement et de choisir sa résidence à l'intérieur d'un Etat. \n" +
                        "\n" +
                        "2. Toute personne a le droit de quitter tout pays, y compris le sien, et de revenir dans son pays.\n" +
                        "\n" +
                        "Article 14\n" +
                        "\n" +
                        "1. Devant la persécution, toute personne a le droit de chercher asile et de bénéficier de l'asile en d'autres pays. \n" +
                        "\n" +
                        "2. Ce droit ne peut être invoqué dans le cas de poursuites réellement fondées sur un crime de droit commun ou sur des agissements contraires aux buts et aux principes des Nations Unies.\n" +
                        "\n" +
                        "Article 15\n" +
                        "\n" +
                        "1. Tout individu a droit à une nationalité. \n" +
                        "\n" +
                        "2. Nul ne peut être arbitrairement privé de sa nationalité, ni du droit de changer de nationalité.\n" +
                        "\n" +
                        "Article 16\n" +
                        "\n" +
                        "1. A partir de l'âge nubile, l'homme et la femme, sans aucune restriction quant à la race, la nationalité ou la religion, ont le droit de se marier et de fonder une famille. Ils ont des droits égaux au regard du mariage, durant le mariage et lors de sa dissolution. \n" +
                        "\n" +
                        "2. Le mariage ne peut être conclu qu'avec le libre et plein consentement des futurs époux. \n" +
                        "\n" +
                        "3. La famille est l'élément naturel et fondamental de la société et a droit à la protection de la société et de l'Etat.\n" +
                        "\n" +
                        "Article 17\n" +
                        "\n" +
                        "1. Toute personne, aussi bien seule qu'en collectivité, a droit à la propriété.\n" +
                        "\n" +
                        "2. Nul ne peut être arbitrairement privé de sa propriété.\n" +
                        "\n" +
                        "Article 18\n" +
                        "\n" +
                        "Toute personne a droit à la liberté de pensée, de conscience et de religion ; ce droit implique la liberté de changer de religion ou de conviction ainsi que la liberté de manifester sa religion ou sa conviction seule ou en commun, tant en public qu'en privé, par l'enseignement, les pratiques, le culte et l'accomplissement des rites.\n" +
                        "\n" +
                        "Article 19\n" +
                        "\n" +
                        "Tout individu a droit à la liberté d'opinion et d'expression, ce qui implique le droit de ne pas être inquiété pour ses opinions et celui de chercher, de recevoir et de répandre, sans considérations de frontières, les informations et les idées par quelque moyen d'expression que ce soit.\n" +
                        "\n" +
                        "Article 20\n" +
                        "\n" +
                        "1. Toute personne a droit à la liberté de réunion et d'association pacifiques. \n" +
                        "\n" +
                        "2. Nul ne peut être obligé de faire partie d'une association.\n" +
                        "\n" +
                        "Article 21\n" +
                        "\n" +
                        "1. Toute personne a le droit de prendre part à la direction des affaires publiques de son pays, soit directement, soit par l'intermédiaire de représentants librement choisis. \n" +
                        "\n" +
                        "2. Toute personne a droit à accéder, dans des conditions d'égalité, aux fonctions publiques de son pays.\n" +
                        "\n" +
                        "3. La volonté du peuple est le fondement de l'autorité des pouvoirs publics ; cette volonté doit s'exprimer par des élections honnêtes qui doivent avoir lieu périodiquement, au suffrage universel égal et au vote secret ou suivant une procédure équivalente assurant la liberté du vote.\n" +
                        "\n" +
                        "Article 22\n" +
                        "\n" +
                        "Toute personne, en tant que membre de la société, a droit à la sécurité sociale ; elle est fondée à obtenir la satisfaction des droits économiques, sociaux et culturels indispensables à sa dignité et au libre développement de sa personnalité, grâce à l'effort national et à la coopération internationale, compte tenu de l'organisation et des ressources de chaque pays.\n" +
                        "\n" +
                        "Article 23\n" +
                        "\n" +
                        "1. Toute personne a droit au travail, au libre choix de son travail, à des conditions équitables et satisfaisantes de travail et à la protection contre le chômage. \n" +
                        "\n" +
                        "2. Tous ont droit, sans aucune discrimination, à un salaire égal pour un travail égal. \n" +
                        "\n" +
                        "3. Quiconque travaille a droit à une rémunération équitable et satisfaisante lui assurant ainsi qu'à sa famille une existence conforme à la dignité humaine et complétée, s'il y a lieu, par tous autres moyens de protection sociale. \n" +
                        "\n" +
                        "4. Toute personne a le droit de fonder avec d'autres des syndicats et de s'affilier à des syndicats pour la défense de ses intérêts.\n" +
                        "\n" +
                        "Article 24\n" +
                        "\n" +
                        "Toute personne a droit au repos et aux loisirs et notamment à une limitation raisonnable de la durée du travail et à des congés payés périodiques.\n" +
                        "\n" +
                        "Article 25\n" +
                        "\n" +
                        "1. Toute personne a droit à un niveau de vie suffisant pour assurer sa santé, son bien-être et ceux de sa famille, notamment pour l'alimentation, l'habillement, le logement, les soins médicaux ainsi que pour les services sociaux nécessaires ; elle a droit à la sécurité en cas de chômage, de maladie, d'invalidité, de veuvage, de vieillesse ou dans les autres cas de perte de ses moyens de subsistance par suite de circonstances indépendantes de sa volonté. \n" +
                        "\n" +
                        "2. La maternité et l'enfance ont droit à une aide et à une assistance spéciales. Tous les enfants, qu'ils soient nés dans le mariage ou hors mariage, jouissent de la même protection sociale.\n" +
                        "\n" +
                        "Article 26\n" +
                        "\n" +
                        "1. Toute personne a droit à l'éducation. L'éducation doit être gratuite, au moins en ce qui concerne l'enseignement élémentaire et fondamental. L'enseignement élémentaire est obligatoire. L'enseignement technique et professionnel doit être généralisé ; l'accès aux études supérieures doit être ouvert en pleine égalité à tous en fonction de leur mérite. \n" +
                        "\n" +
                        "2. L'éducation doit viser au plein épanouissement de la personnalité humaine et au renforcement du respect des droits de l'homme et des libertés fondamentales. Elle doit favoriser la compréhension, la tolérance et l'amitié entre toutes les nations et tous les groupes raciaux ou religieux, ainsi que le développement des activités des Nations Unies pour le maintien de la paix. \n" +
                        "\n" +
                        "3. Les parents ont, par priorité, le droit de choisir le genre d'éducation à donner à leurs enfants.\n" +
                        "\n" +
                        "Article 27\n" +
                        "\n" +
                        "1. Toute personne a le droit de prendre part librement à la vie culturelle de la communauté, de jouir des arts et de participer au progrès scientifique et aux bienfaits qui en résultent. \n" +
                        "\n" +
                        "2. Chacun a droit à la protection des intérêts moraux et matériels découlant de toute production scientifique, littéraire ou artistique dont il est l'auteur.\n" +
                        "\n" +
                        "Article 28\n" +
                        "\n" +
                        "Toute personne a droit à ce que règne, sur le plan social et sur le plan international, un ordre tel que les droits et libertés énoncés dans la présente Déclaration puissent y trouver plein effet.\n" +
                        "\n" +
                        "Article 29\n" +
                        "\n" +
                        "1. L'individu a des devoirs envers la communauté dans laquelle seule le libre et plein développement de sa personnalité est possible. \n" +
                        "\n" +
                        "2. Dans l'exercice de ses droits et dans la jouissance de ses libertés, chacun n'est soumis qu'aux limitations établies par la loi exclusivement en vue d'assurer la reconnaissance et le respect des droits et libertés d'autrui et afin de satisfaire aux justes exigences de la morale, de l'ordre public et du bien-être général dans une société démocratique. \n" +
                        "\n" +
                        "3. Ces droits et libertés ne pourront, en aucun cas, s'exercer contrairement aux buts et aux principes des Nations Unies.\n" +
                        "\n" +
                        "Article 30\n" +
                        "\n" +
                        "Aucune disposition de la présente Déclaration ne peut être interprétée comme impliquant pour un État, un groupement ou un individu un droit quelconque de se livrer à une activité ou d'accomplir un acte visant à la destruction des droits et libertés qui y sont énoncés."
            ),


            TextForReading(
                "Всеобщая декларация прав человека",
                "Преамбула\n" +
                        "\n" +
                        "Принимая во внимание, что признание достоинства, присущего всем членам человеческой семьи, и равных и неотъемлемых прав их является основой свободы, справедливости и всеобщего мира; и\n" +
                        "\n" +
                        "принимая во внимание, что пренебрежение и презрение к правам человека привели к варварским актам, которые возмущают совесть человечества, и что создание такого мира, в котором люди будут иметь свободу слова и убеждений и будут свободны от страха и нужды, провозглашено как высокое стремление людей; и\n" +
                        "\n" +
                        "принимая во внимание, что необходимо, чтобы права человека охранялись властью закона в целях обеспечения того, чтобы человек не был вынужден прибегать, в качестве последнего средства, к восстанию против тирании и угнетения; и\n" +
                        "\n" +
                        "принимая во внимание, что необходимо содействовать развитию дружественных отношений между народами; и\n" +
                        "\n" +
                        "принимая во внимание, что народы Объединенных Наций подтвердили в Уставе свою веру в основные права человека, в достоинство и ценность человеческой личности и в равноправие мужчин и женщин и решили содействовать социальному прогрессу и улучшению условий жизни при большей свободе; и\n" +
                        "\n" +
                        "принимая во внимание, что государства-члены обязались содействовать, в сотрудничестве с Организацией Объединенных Наций, всеобщему уважению и соблюдению прав человека и основных свобод; и\n" +
                        "\n" +
                        "принимая во внимание, что всеобщее понимание характера этих прав и свобод имеет огромное значение для полного выполнения этого обязательства,\n" +
                        "\n" +
                        "Генеральная Ассамблея,\n" +
                        "\n" +
                        "провозглашает настоящую Всеобщую декларацию прав человека в качестве задачи, к выполнению которой должны стремиться все народы и государства с тем, чтобы каждый человек и каждый орган общества, постоянно имея в виду настоящую Декларацию, стремились путем просвещения и образования содействовать уважению этих прав и свобод и обеспечению, путем национальных и международных прогрессивных мероприятий, всеобщего и эффективного признания и осуществления их как среди народов государств-членов Организации, так и среди народов территорий, находящихся под их юрисдикцией.\n" +
                        "\n" +
                        "Статья 1\n" +
                        "\n" +
                        "Все люди рождаются свободными и равными в своем достоинстве и правах. Они наделены разумом и совестью и должны поступать в отношении друг друга в духе братства.\n" +
                        "\n" +
                        "Статья 2\n" +
                        "\n" +
                        "Каждый человек должен обладать всеми правами и всеми свободами, провозглашенными настоящей Декларацией, без какого бы то ни было различия, как-то в отношении расы, цвета кожи, пола, языка, религии, политических или иных убеждений, национального или социального происхождения, имущественного, сословного или иного положения.\n" +
                        "\n" +
                        "Кроме того, не должно проводиться никакого различия на основе политического, правового или международного статуса страны или территории, к которой человек принадлежит, независимо от того, является ли эта территория независимой, подопечной, несамоуправляющейся или как-либо иначе ограниченной в своем суверенитете.\n" +
                        "\n" +
                        "Статья 3\n" +
                        "\n" +
                        "Каждый человек имеет право на жизнь, на свободу и на личную неприкосновенность.\n" +
                        "\n" +
                        "Статья 4\n" +
                        "\n" +
                        "Никто не должен содержаться в рабстве или в подневольном состоянии; рабство и работорговля запрещаются во всех их видах.\n" +
                        "\n" +
                        "Статья 5\n" +
                        "\n" +
                        "Никто не должен подвергаться пыткам или жестоким, бесчеловечным или унижающим его достоинство обращению и наказанию.\n" +
                        "\n" +
                        "Статья 6\n" +
                        "\n" +
                        "Каждый человек, где бы он ни находился, имеет право на признание его правосубъектности.\n" +
                        "\n" +
                        "Статья 7\n" +
                        "\n" +
                        "Все люди равны перед законом и имеют право, без всякого различия, на равную защиту закона. Все люди имеют право на равную защиту от какой бы то ни было дискриминации, нарушающей настоящую Декларацию, и от какого бы то ни было подстрекательства к такой дискриминации.\n" +
                        "\n" +
                        "Статья 8\n" +
                        "\n" +
                        "Каждый человек имеет право на эффективное восстановление в правах компетентными национальными судами в случаях нарушения его основных прав, предоставленных ему конституцией или законом.\n" +
                        "\n" +
                        "Статья 9\n" +
                        "\n" +
                        "Никто не может быть подвергнут произвольному аресту, задержанию или изгнанию.\n" +
                        "\n" +
                        "Статья 10\n" +
                        "\n" +
                        "Каждый человек, для определения его прав и обязанностей и для установления обоснованности предъявленного ему уголовного обвинения, имеет право, на основе полного равенства, на то, чтобы его дело было рассмотрено гласно и с соблюдением всех требований справедливости независимым и беспристрастным судом.\n" +
                        "\n" +
                        "Статья 11\n" +
                        "\n" +
                        "1. Каждый человек, обвиняемый в совершении преступления, имеет право считаться невиновным до тех пор, пока его виновность не будет установлена законным порядком путем гласного судебного разбирательства, при котором ему обеспечиваются все возможности для защиты.\n" +
                        "2. Никто не может быть осужден за преступление на основании совершения какого-либо деяния или за бездействие, которые во время их совершения не составляли преступления по национальным законам или по международному праву. Не может также налагаться наказание более тяжкое, нежели то, которое могло быть применено в то время, когда преступление было совершено.\n" +
                        "\n" +
                        "Статья 12\n" +
                        "\n" +
                        "Никто не может подвергаться произвольному вмешательству в его личную и семейную жизнь, произвольным посягательствам на неприкосновенность его жилища, тайну его корреспонденции или на его честь и репутацию. Каждый человек имеет право на защиту закона от такого вмешательства или таких посягательств.\n" +
                        "\n" +
                        "Статья 13\n" +
                        "\n" +
                        "1. Каждый человек имеет право свободно передвигаться и выбирать себе местожительство в пределах каждого государства.\n" +
                        "2. Каждый человек имеет право покидать любую страну, включая свою собственную, и возвращаться в свою страну.\n" +
                        "\n" +
                        "Статья 14\n" +
                        "\n" +
                        "1. Каждый человек имеет право искать убежища от преследования в других странах и пользоваться этим убежищем.\n" +
                        "2. Это право не может быть использовано в случае преследования, в действительности основанного на совершении неполитического преступления, или деяния, противоречащего целям и принципам Организации Объединенных Наций.\n" +
                        "\n" +
                        "Статья 15\n" +
                        "\n" +
                        "1. Каждый человек имеет право на гражданство.\n" +
                        "2. Никто не может быть произвольно лишен своего гражданства или права изменить свое гражданство.\n" +
                        "\n" +
                        "Статья 16\n" +
                        "\n" +
                        "1. Мужчины и женщины, достигшие совершеннолетия, имеют право без всяких ограничений по признаку расы, национальности или религии вступать в брак и основывать свою семью. Они пользуются одинаковыми правами в отношении вступления в брак, во время состояния в браке и во время его расторжения.\n" +
                        "2. Брак может быть заключен только при свободном и полном согласии обеих вступающих в брак сторон.\n" +
                        "3. Семья является естественной и основной ячейкой общества и имеет право на защиту со стороны общества и государства.\n" +
                        "\n" +
                        "Статья 17\n" +
                        "\n" +
                        "1. Каждый человек имеет право владеть имуществом как единолично, так и совместно с другими.\n" +
                        "2. Никто не должен быть произвольно лишен своего имущества.\n" +
                        "\n" +
                        "Статья 18\n" +
                        "\n" +
                        "Каждый человек имеет право на свободу мысли, совести и религии; это право включает свободу менять свою религию или убеждения и свободу исповедовать свою религию или убеждения как единолично, так и сообща с другими, публичным или частным порядком в учении, богослужении и выполнении религиозных и ритуальных обрядов.\n" +
                        "\n" +
                        "Статья 19\n" +
                        "\n" +
                        "Каждый человек имеет право на свободу убеждений и на свободное выражение их; это право включает свободу беспрепятственно придерживаться своих убеждений и свободу искать, получать и распространять информацию и идеи любыми средствами и независимо от государственных границ.\n" +
                        "\n" +
                        "Статья 20\n" +
                        "\n" +
                        "1. Каждый человек имеет право на свободу мирных собраний и ассоциаций.\n" +
                        "2. Никто не может быть принуждаем вступать в какую-либо ассоциацию.\n" +
                        "\n" +
                        "Статья 21\n" +
                        "\n" +
                        "1. Каждый человек имеет право принимать участие в управлении своей страной непосредственно или через посредство свободно избранных представителей.\n" +
                        "2. Каждый человек имеет право равного доступа к государственной службе в своей стране.\n" +
                        "3. Воля народа должна быть основой власти правительства; эта воля должна находить себе выражение в периодических и нефальсифицированных выборах, которые должны проводиться при всеобщем и равном избирательном праве путем тайного голосования или же посредством других равнозначных форм, обеспечивающих свободу голосования.\n" +
                        "\n" +
                        "Статья 22\n" +
                        "\n" +
                        "Каждый человек, как член общества, имеет право на социальное обеспечение и на осуществление необходимых для поддержания его достоинства и для свободного развития его личности прав в экономической, социальной и культурной областях через посредство национальных усилий и международного сотрудничества и в соответствии со структурой и ресурсами каждого государства.\n" +
                        "\n" +
                        "Статья 23\n" +
                        "\n" +
                        "1. Каждый человек имеет право на труд, на свободный выбор работы, на справедливые и благоприятные условия труда и на защиту от безработицы.\n" +
                        "2. Каждый человек, без какой-либо дискриминации, имеет право на равную оплату за равный труд.\n" +
                        "3. Каждый работающий имеет право на справедливое и удовлетворительное вознаграждение, обеспечивающее достойное человека существование для него самого и его семьи, и дополняемое, при необходимости, другими средствами социального обеспечения.\n" +
                        "4. Каждый человек имеет право создавать профессиональные союзы и входить в профессиональные союзы для защиты своих интересов.\n" +
                        "\n" +
                        "Статья 24\n" +
                        "\n" +
                        "Каждый человек имеет право на отдых и досуг, включая право на разумное ограничение рабочего дня и на оплачиваемый периодический отпуск.\n" +
                        "\n" +
                        "Статья 25\n" +
                        "\n" +
                        "1. Каждый человек имеет право на такой жизненный уровень, включая пищу, одежду, жилище, медицинский уход и необходимое социальное обслуживание, который необходим для поддержания здоровья и благосостояния его самого и его семьи, и право на обеспечение на случай безработицы, болезни, инвалидности, вдовства, наступления старости или иного случая утраты средств к существованию по не зависящим от него обстоятельствам.\n" +
                        "2. Материнство и младенчество дают право на особое попечение и помощь. Все дети, родившиеся в браке или вне брака, должны пользоваться одинаковой социальной защитой.\n" +
                        "\n" +
                        "Статья 26\n" +
                        "\n" +
                        "1. Каждый человек имеет право на образование. Образование должно быть бесплатным по меньшей мере в том, что касается начального и общего образования. Начальное образование должно быть обязательным. Техническое и профессиональное образование должно быть общедоступным, и высшее образование должно быть одинаково доступным для всех на основе способностей каждого.\n" +
                        "2. Образование должно быть направлено к полному развитию человеческой личности и к увеличению уважения к правам человека и основным свободам. Образование должно содействовать взаимопониманию, терпимости и дружбе между всеми народами, расовыми и религиозными группами, и должно содействовать деятельности Организации Объединенных Наций по поддержанию мира.\n" +
                        "3. Родители имеют право приоритета в выборе вида образования для своих малолетних детей.\n" +
                        "\n" +
                        "Статья 27\n" +
                        "\n" +
                        "1. Каждый человек имеет право свободно участвовать в культурной жизни общества, наслаждаться искусством, участвовать в научном прогрессе и пользоваться его благами.\n" +
                        "2. Каждый человек имеет право на защиту его моральных и материальных интересов, являющихся результатом научных, литературных или художественных трудов, автором которых он является.\n" +
                        "\n" +
                        "Статья 28\n" +
                        "\n" +
                        "Каждый человек имеет право на социальный и международный порядок, при котором права и свободы, изложенные в настоящей Декларации, могут быть полностью осуществлены.\n" +
                        "\n" +
                        "Статья 29\n" +
                        "\n" +
                        "1. Каждый человек имеет обязанности перед обществом, в котором только и возможно свободное и полное развитие его личности.\n" +
                        "2. При осуществлении своих прав и свобод каждый человек должен подвергаться только таким ограничениям, какие установлены законом исключительно с целью обеспечения должного признания и уважения прав и свобод других и удовлетворения справедливых требований морали, общественного порядка и общего благосостояния в демократическом обществе.\n" +
                        "3. Осуществление этих прав и свобод ни в коем случае не должно противоречить целям и принципам Организации Объединенных Наций.\n" +
                        "\n" +
                        "Статья 30\n" +
                        "\n" +
                        "Ничто в настоящей Декларации не может быть истолковано, как предоставление какому-либо государству, группе лиц или отдельным лицам права заниматься какой-либо деятельностью или совершать действия, направленные к уничтожению прав и свобод, изложенных в настоящей Декларации."
            ),


            TextForReading(
                "La Declaración Universal de los Derechos Humanos",
                "Preámbulo\n" +
                        "\n" +
                        "Considerando que la libertad, la justicia y la paz en el mundo tienen por base el reconocimiento de la dignidad intrínseca y de los derechos iguales e inalienables de todos los miembros de la familia humana;\n" +
                        "\n" +
                        "Considerando que el desconocimiento y el menosprecio de los derechos humanos han originado actos de barbarie ultrajantes para la conciencia de la humanidad, y que se ha proclamado, como la aspiración más elevada del hombre, el advenimiento de un mundo en que los seres humanos, liberados del temor y de la miseria, disfruten de la libertad de palabra y de la libertad de creencias;\n" +
                        "\n" +
                        "Considerando esencial que los derechos humanos sean protegidos por un régimen de Derecho, a fin de que el hombre no se vea compelido al supremo recurso de la rebelión contra la tiranía y la opresión;\n" +
                        "\n" +
                        "Considerando también esencial promover el desarrollo de relaciones amistosas entre las naciones;\n" +
                        "\n" +
                        "Considerando que los pueblos de las Naciones Unidas han reafirmado en la Carta su fe en los derechos fundamentales del hombre, en la dignidad y el valor de la persona humana y en la igualdad de derechos de hombres y mujeres, y se han declarado resueltos a promover el progreso social y a elevar el nivel de vida dentro de un concepto más amplio de la libertad;\n" +
                        "\n" +
                        "Considerando que los Estados Miembros se han comprometido a asegurar, en cooperación con la Organización de las Naciones Unidas, el respeto universal y efectivo a los derechos y libertades fundamentales del hombre, y\n" +
                        "\n" +
                        "Considerando que una concepción común de estos derechos y libertades es de la mayor importancia para el pleno cumplimiento de dicho compromiso;\n" +
                        "\n" +
                        "Ahora, por tanto,\n" +
                        "\n" +
                        "La Asamblea General,\n" +
                        "\n" +
                        "Proclama la presente Declaración Universal de los Derechos Humanos como ideal común por el que todos los pueblos y naciones deben esforzarse, a fin de que tanto los individuos como las instituciones, inspirándose constantemente en ella, promuevan, mediante la enseñanza y la educación, el respeto a estos derechos y libertades, y aseguren, por medidas progresivas de carácter nacional e internacional, su reconocimiento y aplicación universales y efectivos, tanto entre los pueblos de los Estados Miembros como entre los de los territorios colocados bajo su jurisdicción.  \n" +
                        "\n" +
                        "Artículo 1\n" +
                        "\n" +
                        "Todos los seres humanos nacen libres e iguales en dignidad y derechos y, dotados como están de razón y conciencia, deben comportarse fraternalmente los unos con los otros.\n" +
                        "\n" +
                        "Artículo 2\n" +
                        "\n" +
                        "Toda persona tiene todos los derechos y libertades proclamados en esta Declaración, sin distinción alguna de raza, color, sexo, idioma, religión, opinión política o de cualquier otra índole, origen nacional o social, posición económica, nacimiento o cualquier otra condición. Además, no se hará distinción alguna fundada en la condición política, jurídica o internacional del país o territorio de cuya jurisdicción dependa una persona, tanto si se trata de un país independiente, como de un territorio bajo administración fiduciaria, no autónomo o sometido a cualquier otra limitación de soberanía.\n" +
                        "\n" +
                        "Artículo 3\n" +
                        "\n" +
                        "Todo individuo tiene derecho a la vida, a la libertad y a la seguridad de su persona.\n" +
                        "\n" +
                        "Artículo 4\n" +
                        "\n" +
                        "Nadie estará sometido a esclavitud ni a servidumbre, la esclavitud y la trata de esclavos están prohibidas en todas sus formas.\n" +
                        "\n" +
                        "Artículo 5\n" +
                        "\n" +
                        "Nadie será sometido a torturas ni a penas o tratos crueles, inhumanos o degradantes.\n" +
                        "\n" +
                        "Artículo 6\n" +
                        "\n" +
                        "Todo ser humano tiene derecho, en todas partes, al reconocimiento de su personalidad jurídica.\n" +
                        "\n" +
                        "Artículo 7\n" +
                        "\n" +
                        "Todos son iguales ante la ley y tienen, sin distinción, derecho a igual protección de la ley. Todos tienen derecho a igual protección contra toda discriminación que infrinja esta Declaración y contra toda provocación a tal discriminación.\n" +
                        "\n" +
                        "Artículo 8\n" +
                        "\n" +
                        "Toda persona tiene derecho a un recurso efectivo ante los tribunales nacionales competentes, que la ampare contra actos que violen sus derechos fundamentales reconocidos por la constitución o por la ley.\n" +
                        "\n" +
                        "Artículo 9\n" +
                        "\n" +
                        "Nadie podrá ser arbitrariamente detenido, preso ni desterrado.\n" +
                        "\n" +
                        "Artículo 10\n" +
                        "\n" +
                        "Toda persona tiene derecho, en condiciones de plena igualdad, a ser oída públicamente y con justicia por un tribunal independiente e imparcial, para la determinación de sus derechos y obligaciones o para el examen de cualquier acusación contra ella en materia penal.\n" +
                        "\n" +
                        "Artículo 11\n" +
                        "\n" +
                        "1. Toda persona acusada de delito tiene derecho a que se presuma su inocencia mientras no se pruebe su culpabilidad, conforme a la ley y en juicio público en el que se le hayan asegurado todas las garantías necesarias para su defensa.\n" +
                        "2. Nadie será condenado por actos u omisiones que en el momento de cometerse no fueron delictivos según el Derecho nacional o internacional. Tampoco se impondrá pena más grave que la aplicable en el momento de la comisión del delito.\n" +
                        "\n" +
                        "Artículo 12\n" +
                        "\n" +
                        "Nadie será objeto de injerencias arbitrarias en su vida privada, su familia, su domicilio o su correspondencia, ni de ataques a su honra o a su reputación. Toda persona tiene derecho a la protección de la ley contra tales injerencias o ataques.\n" +
                        "\n" +
                        "Artículo 13\n" +
                        "\n" +
                        "1. Toda persona tiene derecho a circular libremente y a elegir su residencia en el territorio de un Estado.\n" +
                        "2. Toda persona tiene derecho a salir de cualquier país, incluso del propio, y a regresar a su país.\n" +
                        "\n" +
                        "Artículo 14\n" +
                        "\n" +
                        "1. En caso de persecución, toda persona tiene derecho a buscar asilo, y a disfrutar de él, en cualquier país.\n" +
                        "2. Este derecho no podrá ser invocado contra una acción judicial realmente originada por delitos comunes o por actos opuestos a los propósitos y principios de las Naciones Unidas.\n" +
                        "\n" +
                        "Artículo 15\n" +
                        "\n" +
                        "1. Toda persona tiene derecho a una nacionalidad.\n" +
                        "2. A nadie se privará arbitrariamente de su nacionalidad ni del derecho a cambiar de nacionalidad.\n" +
                        "\n" +
                        "Artículo 16\n" +
                        "\n" +
                        "1. Los hombres y las mujeres, a partir de la edad núbil, tienen derecho, sin restricción alguna por motivos de raza, nacionalidad o religión, a casarse y fundar una familia, y disfrutarán de iguales derechos en cuanto al matrimonio, durante el matrimonio y en caso de disolución del matrimonio.\n" +
                        "2. Sólo mediante libre y pleno consentimiento de los futuros esposos podrá contraerse el matrimonio.\n" +
                        "3. La familia es el elemento natural y fundamental de la sociedad y tiene derecho a la protección de la sociedad y del Estado.\n" +
                        "\n" +
                        "Artículo 17\n" +
                        "\n" +
                        "1. Toda persona tiene derecho a la propiedad, individual y colectivamente.\n" +
                        "2. Nadie será privado arbitrariamente de su propiedad.\n" +
                        "\n" +
                        "Artículo 18\n" +
                        "\n" +
                        "Toda persona tiene derecho a la libertad de pensamiento, de conciencia y de religión; este derecho incluye la libertad de cambiar de religión o de creencia, así como la libertad de manifestar su religión o su creencia, individual y colectivamente, tanto en público como en privado, por la enseñanza, la práctica, el culto y la observancia.\n" +
                        "\n" +
                        "Artículo 19\n" +
                        "\n" +
                        "Todo individuo tiene derecho a la libertad de opinión y de expresión; este derecho incluye el de no ser molestado a causa de sus opiniones, el de investigar y recibir informaciones y opiniones, y el de difundirlas, sin limitación de fronteras, por cualquier medio de expresión.\n" +
                        "\n" +
                        "Artículo 20\n" +
                        "\n" +
                        "1. Toda persona tiene derecho a la libertad de reunión y de asociación pacíficas.\n" +
                        "2. Nadie podrá ser obligado a pertenecer a una asociación.\n" +
                        "\n" +
                        "Artículo 21\n" +
                        "\n" +
                        "1. Toda persona tiene derecho a participar en el gobierno de su país, directamente o por medio de representantes libremente escogidos.\n" +
                        "2. Toda persona tiene el derecho de acceso, en condiciones de igualdad, a las funciones públicas de su país.\n" +
                        "3. La voluntad del pueblo es la base de la autoridad del poder público; esta voluntad se expresará mediante elecciones auténticas que habrán de celebrarse periódicamente, por sufragio universal e igual y por voto secreto u otro procedimiento equivalente que garantice la libertad del voto.\n" +
                        "\n" +
                        "Artículo 22\n" +
                        "\n" +
                        "Toda persona, como miembro de la sociedad, tiene derecho a la seguridad social, y a obtener, mediante el esfuerzo nacional y la cooperación internacional, habida cuenta de la organización y los recursos de cada Estado, la satisfacción de los derechos económicos, sociales y culturales, indispensables a su dignidad y al libre desarrollo de su personalidad.\n" +
                        "\n" +
                        "Artículo 23\n" +
                        "\n" +
                        "1. Toda persona tiene derecho al trabajo, a la libre elección de su trabajo, a condiciones equitativas y satisfactorias de trabajo y a la protección contra el desempleo.\n" +
                        "2. Toda persona tiene derecho, sin discriminación alguna, a igual salario por trabajo igual.\n" +
                        "3. Toda persona que trabaja tiene derecho a una remuneración equitativa y satisfactoria, que le asegure, así como a su familia, una existencia conforme a la dignidad humana y que será completada, en caso necesario, por cualesquiera otros medios de protección social.\n" +
                        "4. Toda persona tiene derecho a fundar sindicatos y a sindicarse para la defensa de sus intereses.\n" +
                        "\n" +
                        "Artículo 24\n" +
                        "\n" +
                        "Toda persona tiene derecho al descanso, al disfrute del tiempo libre, a una limitación razonable de la duración del trabajo y a vacaciones periódicas pagadas.\n" +
                        "\n" +
                        "Artículo 25\n" +
                        "\n" +
                        "1. Toda persona tiene derecho a un nivel de vida adecuado que le asegure, así como a su familia, la salud y el bienestar, y en especial la alimentación, el vestido, la vivienda, la asistencia médica y los servicios sociales necesarios; tiene asimismo derecho a los seguros en caso de desempleo, enfermedad, invalidez, viudez, vejez u otros casos de pérdida de sus medios de subsistencia por circunstancias independientes de su voluntad.\n" +
                        "2. La maternidad y la infancia tienen derecho a cuidados y asistencia especiales. Todos los niños, nacidos de matrimonio o fuera de matrimonio, tienen derecho a igual protección social.\n" +
                        "\n" +
                        "Artículo 26\n" +
                        "\n" +
                        "1. Toda persona tiene derecho a la educación. La educación debe ser gratuita, al menos en lo concerniente a la instrucción elemental y fundamental. La instrucción elemental será obligatoria. La instrucción técnica y profesional habrá de ser generalizada; el acceso a los estudios superiores será igual para todos, en función de los méritos respectivos.\n" +
                        "2. La educación tendrá por objeto el pleno desarrollo de la personalidad humana y el fortalecimiento del respeto a los derechos humanos y a las libertades fundamentales; favorecerá la comprensión, la tolerancia y la amistad entre todas las naciones y todos los grupos étnicos o religiosos, y promoverá el desarrollo de las actividades de las Naciones Unidas para el mantenimiento de la paz.\n" +
                        "3. Los padres tendrán derecho preferente a escoger el tipo de educación que habrá de darse a sus hijos.\n" +
                        "\n" +
                        "Artículo 27\n" +
                        "\n" +
                        "1. Toda persona tiene derecho a tomar parte libremente en la vida cultural de la comunidad, a gozar de las artes y a participar en el progreso científico y en los beneficios que de él resulten.\n" +
                        "2. Toda persona tiene derecho a la protección de los intereses morales y materiales que le correspondan por razón de las producciones científicas, literarias o artísticas de que sea autora.\n" +
                        "\n" +
                        "Artículo 28\n" +
                        "\n" +
                        "Toda persona tiene derecho a que se establezca un orden social e internacional en el que los derechos y libertades proclamados en esta Declaración se hagan plenamente efectivos.\n" +
                        "\n" +
                        "Artículo 29\n" +
                        "\n" +
                        "1. Toda persona tiene deberes respecto a la comunidad, puesto que sólo en ella puede desarrollar libre y plenamente su personalidad.\n" +
                        "2. En el ejercicio de sus derechos y en el disfrute de sus libertades, toda persona estará solamente sujeta a las limitaciones establecidas por la ley con el único fin de asegurar el reconocimiento y el respeto de los derechos y libertades de los demás, y de satisfacer las justas exigencias de la moral, del orden público y del bienestar general en una sociedad democrática.\n" +
                        "3. Estos derechos y libertades no podrán, en ningún caso, ser ejercidos en oposición a los propósitos y principios de las Naciones Unidas.\n" +
                        "\n" +
                        "Artículo 30\n" +
                        "\n" +
                        "Nada en esta Declaración podrá interpretarse en el sentido de que confiere derecho alguno al Estado, a un grupo o a una persona, para emprender y desarrollar actividades o realizar actos tendientes a la supresión de cualquiera de los derechos y libertades proclamados en esta Declaración."
            ),


            TextForReading(
                "Dysphoria",
                "Dysphoria is a unique condition in which a person experiences distress caused by the presence of masculine or feminine traits in their body\n" +
                        "\n" +
                        "Dysphoria is an innate characteristic. Every person is born with either the presence or absence of dysphoria. It cannot be acquired through life experiences, nor is there any way to completely eliminate it\n" +
                        "\n" +
                        "The cause of dysphoria lies in the differences between male and female brains, which influence, to some extent, how a person behaves in life. The vast majority of people have a brain and body that align with the same sex. However, there is a chance that a person may be born with a body and brain of different sexes. Approximately 0.5% of people are born with such characteristics, and their occurrence is independent of the environment or society they are born into\n" +
                        "\n" +
                        "The fact that people can have a brain and body of different sexes has been confirmed through scientific studies using MRI. These studies have also established a connection between such traits and the presence of dysphoria\n" +
                        "\n" +
                        "Dysphoria is not a \"choice\" or a result of upbringing or life experiences. It often manifests at a young age, typically between 7 and 10 years old, and can intensify over time. The suffering caused by dysphoria is one of the most intense feelings a person can experience and often leads to apathy, depression, and more serious consequences\n" +
                        "\n" +
                        "Dysphoria cannot be \"cured\", but there is a way to alleviate the suffering. This involves aligning a person’s body with the sex of their brain, i.e., undergoing a gender transition. One of the most critical sources of suffering in dysphoria is hormonal imbalance. Hormone therapy helps normalize the hormonal background to align with the brain\n" +
                        "\n" +
                        "A person born without transgender dysphoria would never desire to change their body’s sex, as they are naturally deterred by an innate self-preservation mechanism. In transgender individuals, this same mechanism works in the opposite direction, pushing them toward a gender transition. This mechanism is the primary reason why normal people often struggle to understand the desires of transgender people, as they are incapable of experiencing the same feelings\n" +
                        "\n" +
                        "Social acceptance plays a significant role in the lives of transgender individuals. Trans people, like ordinary people, need understanding and moral support, without which their lives can be very difficult and dangerous\n" +
                        "\n" +
                        "A person who undergoes a gender transition and successfully integrates into society is freed from their suffering and can live a fulfilling life\n" +
                        "\n" +
                        "The primary drawback of a gender transition is that hormone therapy typically results in infertility, which is a significant aspect of life for many people. Those undergoing a transition must sacrifice this possibility to relieve the suffering associated with dysphoria\n" +
                        "\n" +
                        "It is important to note that gender transition is a highly individual process. Not all transgender individuals choose medical interventions, and each person determines what is best for themselves"
            ),


            TextForReading(
                "Дисфория",
                "Дисфория это особое состояние при котором человек испьтьвает страдания от проявления маскулинньх или феминньх признаков у своего тела\n" +
                        "\n" +
                        "Дисфория является врожденной особенностью. Каждьй человек рождается с наличием или отсутствием дисфории. Дисфорию нельзя получить в следствии какого либо жизненного опьта, а также не существует способа избавится от нее\n" +
                        "\n" +
                        "Причиной дисфории является то, что женский и мужской мозг имеют различия, которье в определенной степени влияют на то, как будет вести себя человек в жизни. Подавляющее количество людей имеет мозг и тело одного пола, однако существует вероятность того что тело и мозг человека при рождении будут от разньх полов. Приблизително 0.5% людей рождаются с такого рода особенностью и их рождение не зависит от той средь и общества где они родились\n" +
                        "\n" +
                        "Тот факт что люди могут иметь тело и мозг различньх полов бьл подтвержден ученьми при помощи исследований на МРТ, где также бьла уставовлена связь подобной особенности с наличием дисфории\n" +
                        "\n" +
                        "Дисфория не является «выбором» или результатом воспитания или полученого опьта. Зачастую дисфория проявляется в раннем возрасте, как правило 7–10 лет и может усиливаться с течением времени. Страдания вьзванье дисфорией крайне сильнье, это одни из самьх сильньх чувств которье может испьтьвать человек и которье зачастую могут вести к аппатии, депрессии и более тяжельм последствиям\n" +
                        "\n" +
                        "Дисфорию нельзя вьлечить, однако существует способ сделать так чтобь человек не страдал. Этим способом является привединие тела человека к полу его мозга т.е. совершение транс перехода. Одна из важнейших вещей вьзьвающих страдания при дисфории это баланс гормонов. Гормонотерапия помогает привести гормонольньй фон в норму подходящую для мозга\n" +
                        "\n" +
                        "Человек рожденньй без трансгендерной дисфории никогда не захочет изменить пол своего тела, поскольку его будет останавливать природньй механизм по защите себя. У транс персон этот самьй механизм работает в обратную сторону, подталкивая человека к совершению транс перехода. Данньй механизм это основная причина того, почему обьчнье люди не могут понять желания транс людей. Обьчнье люди в принципе не способнь испьтьвать то, что ощущают транс люди\n" +
                        "\n" +
                        "Социальное принятие играет значительную роль в жизни транс персон. Транс люди как и обьчнье люди нуждаются в понимании и моральной поддержке, без которой их жизнь может бьть крайне сложной и опасной\n" +
                        "\n" +
                        "Человек совершивший транс переход и встроевшийся в общество избавляется от страданий и имеет возможность жить полноценной жизнью\n" +
                        "\n" +
                        "Основньм неудобстом транс перехода является то, что гармонотерапия влечет за собой невозможность рождения детей которье являются важной частью жизни многих людей. Людям совершающим транс-переход приходится жертвовать этой возможнстью ради того чтобь избавится от страданий связаньх с дисфорией\n" +
                        "\n" +
                        "Важно отметить, что транс переход является весьма индивидуальным процессом, не все трансгендерные люди выбирают медицинские вмешательства, и каждый человек сам определяет, что лучше для него"
            ),


            /*TextForReading(
                "",
                ""
            ),


            TextForReading(
                "",
                ""
            ),*/
        )


        fun getTextsForReading(): Array<TextForReading> {
            return textsFarReadingArray
        }


        private val parameterDescriptionArray= arrayOf(

            // SpectrumInfo

            ParameterInfo(
                "SpectrogramInHz",
                "Spectrogram",
                "A visual representation of the spectrum of frequencies in a sound signal as it varies with time"
            ),
            ParameterInfo(
                "EnergySpectrogramInHz",
                "Energy Spectrogram",
                "A spectrogram highlighting the energy levels across different frequencies in the signal"
            ),
            ParameterInfo(
                "Spectrum",
                "Spectrum",
                "A visual representation of the spectrum of frequencies in a sound"
            ),

            // Static

            ParameterInfo(
                "Loudness",
                "Loudness",
                "A measure of the perceived volume of the sound, calculated based on the sound pressure level"
            ),
            ParameterInfo(
                "Pitch",
                "Pitch",
                "The perceived frequency of a sound, representing how high or low a tone is"
            ),
            ParameterInfo(
                "FirstFormant",
                "First Formant",
                "The first resonant frequency"
            ),
            ParameterInfo(
                "SecondFormant",
                "Second Formant",
                "The second resonant frequency"
            ),
            ParameterInfo(
                "ActiveFirstFormant",
                "AV First Formant",
                "The first active resonant frequency of the voice, also known as F1, critical for determining vowel quality"
            ),
            ParameterInfo(
                "ActiveSecondFormant",
                "AV Second Formant",
                "The second active resonant frequency of the voice, also known as F2, important for distinguishing vowel sounds"
            ),
            ParameterInfo(
                "Energy",
                "Energy",
                "The total power or intensity of the sound signal at the pitch frequency over time"
            ),
            ParameterInfo(
                "H1H2EnergyBalance",
                "1st/2nd Harmonic Balance",
                "The ratio of energy between the first harmonic (H1) and the second harmonic (H2)"
            ),
            ParameterInfo(
                "VoiceWeight",
                "Voice Weight",
                "A parameter of a vowel sounds in a voice that determines the resonance behavior"
            ),
            ParameterInfo(
                "HarmonicToNoiseRatio",
                "Harmonic to Noise Ratio",
                "A measure of the proportion of harmonic (periodic) energy to noise (non-periodic) energy in the signal"
            ),
            ParameterInfo(
                "BandEnergyRatioLow",
                "BER Low",
                "The ratio of energy in low-frequency bands compared to the total energy of the sound"
            ),
            ParameterInfo(
                "BandEnergyRatioMedium",
                "BER Medium",
                "The ratio of energy in medium-frequency bands compared to the total energy of the sound"
            ),
            ParameterInfo(
                "BandEnergyRatioHigh",
                "BER High",
                "The ratio of energy in high-frequency bands compared to the total energy of the sound"
            ),
            ParameterInfo(
                "HLRatio",
                "H/L Ratio",
                "The ratio of high-frequency to low-frequency energy energy in the signal"
            ),
            ParameterInfo(
                "VAD",
                "Voice detection",
                "A technical parameter indicating whether voice activity is present in the audio signal"
            ),
            ParameterInfo(
                "SpectralCentroid",
                "Spectral Centroid",
                "A measure of the 'center of mass' of the spectrum, representing the average frequency weighted by magnitude"
            ),
            ParameterInfo(
                "SpectralTilt",
                "Spectral Tilt",
                "The slope of the spectral envelope, indicating how energy is distributed across frequencies"
            ),
            ParameterInfo(
                "SpectralSpread",
                "Spectral Spread",
                "A measure of the variance or spread of frequencies around the spectral centroid"
            ),

            // Semi-Dynamic

            ParameterInfo(
                "Jitter",
                "Jitter",
                "A measure of the frequency stability, representing variations in the pitch of a sound"
            ),
            ParameterInfo(
                "Shimmer",
                "Shimmer",
                "A measure of the amplitude stability, representing variations in the loudness of a sound"
            ),

            // Dynamic

            ParameterInfo(
                "Prosody",
                "Prosody",
                "An indication of how much the pitch changes duritg a speech"
            ),
            ParameterInfo(
                "Rythm",
                "Rythm (BPM)",
                "Measures the number of beats (separated vowel sounds) per minute"
            ),
            ParameterInfo(
                "Clarity",
                "Clarity",
                "A parameter that shows the ratio of the voice diring speech to the noise during silence"
            ),
            ParameterInfo(
                "PausesDuration",
                "Duration of Pauses",
                "Ratio of time of silence to time of speech"
            ),

            // Special

            ParameterInfo(
                "NoteGraph",
                "Note Graph",
                "A graph displaying the pitch of the sound over time, mapped to corresponding musical notes"
            )
        )


        fun getParameterDescription(parameterId: String): ParameterInfo {
            for (parameterDescription in parameterDescriptionArray) {
                if (parameterDescription.getParameterId() == parameterId) {
                    return  parameterDescription
                }
            }
            return ParameterInfo("", "", "")
        }
    }
}