package controller;

import csvClasses.dataType.FloatArray;
import csvClasses.dataType.StringArray;

import java.util.Arrays;

public class DataCsvRow
{
    private int nid;
    private String originalId;
    private String name;
    private String projectAcronym;
    private String visual;
    private String projectDescription;
    private String results;
    private String coordinators;
    private String[] partners;
    private String[] projectAddresses;
    private String[] projectPostalCodes;
    private String[] projectTowns;
    private String[] projectCountryies;
    private float[] projectLocationLatitude;
    private float[] projectLocationLongitude;
    private String linkToAVideo;
    private String timeframeStart;
    private String timeframeEnd;
    private String projectWebpage;
    private String[] relatedLinks;
    private String euBudgetMffHeading;
    private String programmeName;
    private String[] fundingArea;
    private String[] ecsPriorities;
    private int euBudgetContribution;
    private int totalProjectBudget;
    private String author;
    private String language;

    @Override
    public String toString()
    {
        return "DataCsvRow{" +
                "nid=" + nid +
                ", originalId='" + originalId + '\'' +
                ", name='" + name + '\'' +
                ", projectAcronym='" + projectAcronym + '\'' +
                ", visual='" + visual + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", results='" + results + '\'' +
                ", coordinators='" + coordinators + '\'' +
                ", partners=" + Arrays.toString(partners) +
                ", projectAddresses=" + Arrays.toString(projectAddresses) +
                ", projectPostalCodes=" + Arrays.toString(projectPostalCodes) +
                ", projectTowns=" + Arrays.toString(projectTowns) +
                ", projectCountryies=" + Arrays.toString(projectCountryies) +
                ", projectLocationLatitude=" + Arrays.toString(projectLocationLatitude) +
                ", projectLocationLongitude=" + Arrays.toString(projectLocationLongitude) +
                ", linkToAVideo='" + linkToAVideo + '\'' +
                ", timeframeStart='" + timeframeStart + '\'' +
                ", timeframeEnd='" + timeframeEnd + '\'' +
                ", projectWebpage='" + projectWebpage + '\'' +
                ", relatedLinks=" + Arrays.toString(relatedLinks) +
                ", euBudgetMffHeading='" + euBudgetMffHeading + '\'' +
                ", programmeName='" + programmeName + '\'' +
                ", fundingArea=" + Arrays.toString(fundingArea) +
                ", ecsPriorities=" + Arrays.toString(ecsPriorities) +
                ", euBudgetContribution=" + euBudgetContribution +
                ", totalProjectBudget=" + totalProjectBudget +
                ", author='" + author + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    public int getNid()
    {
        return nid;
    }

    public void setNid(Integer nid)
    {
        this.nid = nid;
    }

    public String getOriginalId()
    {
        return originalId;
    }

    public void setOriginalId(String originalId)
    {
        this.originalId = originalId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getProjectAcronym()
    {
        return projectAcronym;
    }

    public void setProjectAcronym(String projectAcronym)
    {
        this.projectAcronym = projectAcronym;
    }

    public String getVisual()
    {
        return visual;
    }

    public void setVisual(String visual)
    {
        this.visual = visual;
    }

    public String getProjectDescription()
    {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription)
    {
        this.projectDescription = projectDescription;
    }

    public String getResults()
    {
        return results;
    }

    public void setResults(String results)
    {
        this.results = results;
    }

    public String getCoordinators()
    {
        return coordinators;
    }

    public void setCoordinators(String coordinators)
    {
        this.coordinators = coordinators;
    }

    public String[] getPartners()
    {
        return partners;
    }

    public void setPartners(StringArray partners)
    {
        this.partners = partners.getData();
    }

    public String[] getProjectAddresses()
    {
        return projectAddresses;
    }

    public void setProjectAddresses(StringArray projectAddresses)
    {
        this.projectAddresses = projectAddresses.getData();
    }

    public String[] getProjectPostalCodes()
    {
        return projectPostalCodes;
    }

    public void setProjectPostalCodes(StringArray projectPostalCodes)
    {
        this.projectPostalCodes = projectPostalCodes.getData();
    }

    public String[] getProjectTowns()
    {
        return projectTowns;
    }

    public void setProjectTowns(StringArray projectTowns)
    {
        this.projectTowns = projectTowns.getData();
    }

    public String[] getProjectCountryies()
    {
        return projectCountryies;
    }

    public void setProjectCountryies(StringArray projectCountryies)
    {
        this.projectCountryies = projectCountryies.getData();
    }

    public float[] getProjectLocationLatitude()
    {
        return projectLocationLatitude;
    }

    public void setProjectLocationLatitude(FloatArray projectLocationLatitude)
    {
        this.projectLocationLatitude = projectLocationLatitude.getData();
    }

    public float[] getProjectLocationLongitude()
    {
        return projectLocationLongitude;
    }

    public void setProjectLocationLongitude(FloatArray projectLocationLongitude)
    {
        this.projectLocationLongitude = projectLocationLongitude.getData();
    }

    public String getLinkToAVideo()
    {
        return linkToAVideo;
    }

    public void setLinkToAVideo(String linkToAVideo)
    {
        this.linkToAVideo = linkToAVideo;
    }

    public String getTimeframeStart()
    {
        return timeframeStart;
    }

    public void setTimeframeStart(String timeframeStart)
    {
        this.timeframeStart = timeframeStart;
    }

    public String getTimeframeEnd()
    {
        return timeframeEnd;
    }

    public void setTimeframeEnd(String timeframeEnd)
    {
        this.timeframeEnd = timeframeEnd;
    }

    public String getProjectWebpage()
    {
        return projectWebpage;
    }

    public void setProjectWebpage(String projectWebpage)
    {
        this.projectWebpage = projectWebpage;
    }

    public String[] getRelatedLinks()
    {
        return relatedLinks;
    }

    public void setRelatedLinks(StringArray relatedLinks)
    {
        this.relatedLinks = relatedLinks.getData();
    }

    public String getEuBudgetMffHeading()
    {
        return euBudgetMffHeading;
    }

    public void setEuBudgetMffHeading(String euBudgetMffHeading)
    {
        this.euBudgetMffHeading = euBudgetMffHeading;
    }

    public String getProgrammeName()
    {
        return programmeName;
    }

    public void setProgrammeName(String programmeName)
    {
        this.programmeName = programmeName;
    }

    public String[] getFundingArea()
    {
        return fundingArea;
    }

    public void setFundingArea(StringArray fundingArea)
    {
        this.fundingArea = fundingArea.getData();
    }

    public String[] getEcsPriorities()
    {
        return ecsPriorities;
    }

    public void setEcsPriorities(StringArray ecsPriorities)
    {
        this.ecsPriorities = ecsPriorities.getData();
    }

    public int getEuBudgetContribution()
    {
        return euBudgetContribution;
    }

    public void setEuBudgetContribution(Integer euBudgetContribution)
    {
        this.euBudgetContribution = euBudgetContribution;
    }

    public int getTotalProjectBudget()
    {
        return totalProjectBudget;
    }

    public void setTotalProjectBudget(Integer totalProjectBudget)
    {
        this.totalProjectBudget = totalProjectBudget;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }
}
