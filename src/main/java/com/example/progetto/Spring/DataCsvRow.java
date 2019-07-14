package com.example.progetto.Spring;

import csvClasses.dataType.ObjArray;
import csvClasses.dataType.UrlWithDescription;


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
    private ObjArray<String> partners;
    private ObjArray<String> projectAddresses;
    private ObjArray<String> projectPostalCodes;
    private ObjArray<String> projectTowns;
    private ObjArray<String> projectCountryies;
    private ObjArray<Float> projectLocationLatitude;
    private ObjArray<Float> projectLocationLongitude;
    private String linkToAVideo;
    private String timeframeStart;
    private String timeframeEnd;
    private String projectWebpage;
    private ObjArray<UrlWithDescription> relatedLinks;
    private String euBudgetMffHeading;
    private String programmeName;
    private ObjArray<String> fundingArea;
    private ObjArray<String> ecsPriorities;
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
                ", partners=" + partners +
                ", projectAddresses=" + projectAddresses +
                ", projectPostalCodes=" + projectPostalCodes +
                ", projectTowns=" + projectTowns +
                ", projectCountryies=" + projectCountryies +
                ", projectLocationLatitude=" + projectLocationLatitude +
                ", projectLocationLongitude=" + projectLocationLongitude +
                ", linkToAVideo='" + linkToAVideo + '\'' +
                ", timeframeStart='" + timeframeStart + '\'' +
                ", timeframeEnd='" + timeframeEnd + '\'' +
                ", projectWebpage='" + projectWebpage + '\'' +
                ", relatedLinks=" + relatedLinks +
                ", euBudgetMffHeading='" + euBudgetMffHeading + '\'' +
                ", programmeName='" + programmeName + '\'' +
                ", fundingArea=" + fundingArea +
                ", ecsPriorities=" + ecsPriorities +
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
        return partners.getData();
    }

    public void setPartners(ObjArray<String> partners)
    {
        this.partners = partners;
    }

    public String[] getProjectAddresses()
    {
        return projectAddresses.getData();
    }

    public void setProjectAddresses(ObjArray<String> projectAddresses)
    {
        this.projectAddresses = projectAddresses;
    }

    public String[] getProjectPostalCodes()
    {
        return projectPostalCodes.getData();
    }

    public void setProjectPostalCodes(ObjArray<String> projectPostalCodes)
    {
        this.projectPostalCodes = projectPostalCodes;
    }

    public String[] getProjectTowns()
    {
        return projectTowns.getData();
    }

    public void setProjectTowns(ObjArray<String> projectTowns)
    {
        this.projectTowns = projectTowns;
    }

    public String[] getProjectCountryies()
    {
        return projectCountryies.getData();
    }

    public void setProjectCountryies(ObjArray<String> projectCountryies)
    {
        this.projectCountryies = projectCountryies;
    }

    public ObjArray<Float> getProjectLocationLatitude()
    {
        return projectLocationLatitude;
    }

    public void setProjectLocationLatitude(ObjArray<Float> projectLocationLatitude)
    {
        this.projectLocationLatitude = projectLocationLatitude;
    }

    public ObjArray<Float> getProjectLocationLongitude()
    {
        return projectLocationLongitude;
    }

    public void setProjectLocationLongitude(ObjArray<Float> projectLocationLongitude)
    {
        this.projectLocationLongitude = projectLocationLongitude;
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

    public UrlWithDescription[] getRelatedLinks()
    {
        return relatedLinks.getData();
    }

    public void setRelatedLinks(ObjArray<UrlWithDescription> relatedLinks)
    {
        this.relatedLinks = relatedLinks;
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
        return fundingArea.getData();
    }

    public void setFundingArea(ObjArray<String> fundingArea)
    {
        this.fundingArea = fundingArea;
    }

    public String[] getEcsPriorities()
    {
        return ecsPriorities.getData();
    }

    public void setEcsPriorities(ObjArray<String> ecsPriorities)
    {
        this.ecsPriorities = ecsPriorities;
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
