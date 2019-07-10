package com.example.progetto;

import java.util.Arrays;

public class dataCsvRow
{
    int id;
    int Nid;
    int Original_ID;
    String Name;
    String Project_acronym;
    String Visual;
    String Project_description;
    String Results;
    String[] Coordinators;
    String[] Partners;
    String[] Project_address_es;
    String Project_postal_code_s;
    String Project_town_s;
    String[] Project_country_ies;
    float[] Project_location_latitude;
    float[] Project_location_longitude;
    String Link_to_a_video;
    String Timeframe_start;
    String Timeframe_end;
    String Project_webpage;
    String Related_links;
    String EU_Budget_MFF_heading;
    String Programme_name;
    String Funding_area;
    String EC_s_priorities;
    long EU_Budget_contribution;
    long Total_project_budget;
    String Author;
    String Language;


    public dataCsvRow(int id, int nid, int original_ID, String name, String project_acronym, String visual, String project_description, String results, String[] coordinators, String[] partners, String[] project_address_es, String project_postal_code_s, String project_town_s, String[] project_country_ies, float[] project_location_latitude, float[] project_location_longitude, String link_to_a_video, String timeframe_start, String timeframe_end, String project_webpage, String related_links, String EU_Budget_MFF_heading, String programme_name, String funding_area, String EC_s_priorities, long EU_Budget_contribution, long total_project_budget, String author, String language)
    {
        this.id = id;
        Nid = nid;
        Original_ID = original_ID;
        Name = name;
        Project_acronym = project_acronym;
        Visual = visual;
        Project_description = project_description;
        Results = results;
        Coordinators = coordinators;
        Partners = partners;
        Project_address_es = project_address_es;
        Project_postal_code_s = project_postal_code_s;
        Project_town_s = project_town_s;
        Project_country_ies = project_country_ies;
        Project_location_latitude = project_location_latitude;
        Project_location_longitude = project_location_longitude;
        Link_to_a_video = link_to_a_video;
        Timeframe_start = timeframe_start;
        Timeframe_end = timeframe_end;
        Project_webpage = project_webpage;
        Related_links = related_links;
        this.EU_Budget_MFF_heading = EU_Budget_MFF_heading;
        Programme_name = programme_name;
        Funding_area = funding_area;
        this.EC_s_priorities = EC_s_priorities;
        this.EU_Budget_contribution = EU_Budget_contribution;
        Total_project_budget = total_project_budget;
        Author = author;
        Language = language;
    }


    @Override
    public String toString()
    {
        return "dataCsvRow{" +
                "id=" + id +
                ", Nid=" + Nid +
                ", Original_ID=" + Original_ID +
                ", Name='" + Name + '\'' +
                ", Project_acronym='" + Project_acronym + '\'' +
                ", Visual='" + Visual + '\'' +
                ", Project_description='" + Project_description + '\'' +
                ", Results='" + Results + '\'' +
                ", Coordinators=" + Arrays.toString(Coordinators) +
                ", Partners=" + Arrays.toString(Partners) +
                ", Project_address_es=" + Arrays.toString(Project_address_es) +
                ", Project_postal_code_s='" + Project_postal_code_s + '\'' +
                ", Project_town_s='" + Project_town_s + '\'' +
                ", Project_country_ies=" + Arrays.toString(Project_country_ies) +
                ", Project_location_latitude=" + Arrays.toString(Project_location_latitude) +
                ", Project_location_longitude=" + Arrays.toString(Project_location_longitude) +
                ", Link_to_a_video='" + Link_to_a_video + '\'' +
                ", Timeframe_start='" + Timeframe_start + '\'' +
                ", Timeframe_end='" + Timeframe_end + '\'' +
                ", Project_webpage='" + Project_webpage + '\'' +
                ", Related_links='" + Related_links + '\'' +
                ", EU_Budget_MFF_heading='" + EU_Budget_MFF_heading + '\'' +
                ", Programme_name='" + Programme_name + '\'' +
                ", Funding_area='" + Funding_area + '\'' +
                ", EC_s_priorities='" + EC_s_priorities + '\'' +
                ", EU_Budget_contribution=" + EU_Budget_contribution +
                ", Total_project_budget=" + Total_project_budget +
                ", Author='" + Author + '\'' +
                ", Language='" + Language + '\'' +
                '}';
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getNid()
    {
        return Nid;
    }

    public void setNid(int nid)
    {
        Nid = nid;
    }

    public int getOriginal_ID()
    {
        return Original_ID;
    }

    public void setOriginal_ID(int original_ID)
    {
        Original_ID = original_ID;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getProject_acronym()
    {
        return Project_acronym;
    }

    public void setProject_acronym(String project_acronym)
    {
        Project_acronym = project_acronym;
    }

    public String getVisual()
    {
        return Visual;
    }

    public void setVisual(String visual)
    {
        Visual = visual;
    }

    public String getProject_description()
    {
        return Project_description;
    }

    public void setProject_description(String project_description)
    {
        Project_description = project_description;
    }

    public String getResults()
    {
        return Results;
    }

    public void setResults(String results)
    {
        Results = results;
    }

    public String[] getCoordinators()
    {
        return Coordinators;
    }

    public void setCoordinators(String[] coordinators)
    {
        Coordinators = coordinators;
    }

    public String[] getPartners()
    {
        return Partners;
    }

    public void setPartners(String[] partners)
    {
        Partners = partners;
    }

    public String[] getProject_address_es()
    {
        return Project_address_es;
    }

    public void setProject_address_es(String[] project_address_es)
    {
        Project_address_es = project_address_es;
    }

    public String getProject_postal_code_s()
    {
        return Project_postal_code_s;
    }

    public void setProject_postal_code_s(String project_postal_code_s)
    {
        Project_postal_code_s = project_postal_code_s;
    }

    public String getProject_town_s()
    {
        return Project_town_s;
    }

    public void setProject_town_s(String project_town_s)
    {
        Project_town_s = project_town_s;
    }

    public String[] getProject_country_ies()
    {
        return Project_country_ies;
    }

    public void setProject_country_ies(String[] project_country_ies)
    {
        Project_country_ies = project_country_ies;
    }

    public float[] getProject_location_latitude()
    {
        return Project_location_latitude;
    }

    public void setProject_location_latitude(float[] project_location_latitude)
    {
        Project_location_latitude = project_location_latitude;
    }

    public float[] getProject_location_longitude()
    {
        return Project_location_longitude;
    }

    public void setProject_location_longitude(float[] project_location_longitude)
    {
        Project_location_longitude = project_location_longitude;
    }

    public String getLink_to_a_video()
    {
        return Link_to_a_video;
    }

    public void setLink_to_a_video(String link_to_a_video)
    {
        Link_to_a_video = link_to_a_video;
    }

    public String getTimeframe_start()
    {
        return Timeframe_start;
    }

    public void setTimeframe_start(String timeframe_start)
    {
        Timeframe_start = timeframe_start;
    }

    public String getTimeframe_end()
    {
        return Timeframe_end;
    }

    public void setTimeframe_end(String timeframe_end)
    {
        Timeframe_end = timeframe_end;
    }

    public String getProject_webpage()
    {
        return Project_webpage;
    }

    public void setProject_webpage(String project_webpage)
    {
        Project_webpage = project_webpage;
    }

    public String getRelated_links()
    {
        return Related_links;
    }

    public void setRelated_links(String related_links)
    {
        Related_links = related_links;
    }

    public String getEU_Budget_MFF_heading()
    {
        return EU_Budget_MFF_heading;
    }

    public void setEU_Budget_MFF_heading(String EU_Budget_MFF_heading)
    {
        this.EU_Budget_MFF_heading = EU_Budget_MFF_heading;
    }

    public String getProgramme_name()
    {
        return Programme_name;
    }

    public void setProgramme_name(String programme_name)
    {
        Programme_name = programme_name;
    }

    public String getFunding_area()
    {
        return Funding_area;
    }

    public void setFunding_area(String funding_area)
    {
        Funding_area = funding_area;
    }

    public String getEC_s_priorities()
    {
        return EC_s_priorities;
    }

    public void setEC_s_priorities(String EC_s_priorities)
    {
        this.EC_s_priorities = EC_s_priorities;
    }

    public long getEU_Budget_contribution()
    {
        return EU_Budget_contribution;
    }

    public void setEU_Budget_contribution(long EU_Budget_contribution)
    {
        this.EU_Budget_contribution = EU_Budget_contribution;
    }

    public long getTotal_project_budget()
    {
        return Total_project_budget;
    }

    public void setTotal_project_budget(long total_project_budget)
    {
        Total_project_budget = total_project_budget;
    }

    public String getAuthor()
    {
        return Author;
    }

    public void setAuthor(String author)
    {
        Author = author;
    }

    public String getLanguage()
    {
        return Language;
    }

    public void setLanguage(String language)
    {
        Language = language;
    }
}
