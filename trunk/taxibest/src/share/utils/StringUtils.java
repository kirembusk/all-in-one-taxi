package share.utils;


import java.io.*;
import java.util.*;
import java.text.*;

import share.html.*;


/**
 * Class StringUtils
 *
 */
public class StringUtils
{

	public static String replaceString(String s, String sMatch, String sReplace)
	{
		if (sReplace == null)
			sReplace = "";

		if (sMatch == null || "".equals(sMatch) || sMatch.equals(sReplace))
			return s;

		if (s == null || s.equals(""))
		{
			return "";
		}

		int i = 0;
		int j = s.indexOf(sMatch);

		if (j < 0)
		{
			return s;
		}

		StringBuffer sb = new StringBuffer(s.length());

		while (true)
		{
			sb.append(s.substring(i, j));
			sb.append(sReplace);

			i = j + sMatch.length();
			j = s.indexOf(sMatch, i);

			if (j < 0)
			{
				sb.append(s.substring(i));
				break;
			}
		}

		return sb.toString();
	}


	public static String escapeDelimiter(String s, String sEscaper, String sDelimiter, String sBackendDelimiter)
	{
		if (s == null || "".equals(s))
			return "";

		StringBuffer sbResult = new StringBuffer();
		for (int i = 0; i < s.length(); i++)
		{
			if (s.startsWith("" + sEscaper + sEscaper, i))
			{
				sbResult.append(sEscaper);
				i++;
			}
			else if (s.startsWith("" + sEscaper + sDelimiter, i))
			{
				sbResult.append(sDelimiter);
				i++;
			}
			else if (s.startsWith("" + sDelimiter, i))
			{
				sbResult.append(sBackendDelimiter);
			}
			else
			{
				sbResult.append(s.charAt(i));
			}
		}

		return sbResult.toString();
	}


	public static String escapeForJS(String txt)
	{
		return escapeForJS(txt, false);
	}



	public static String escapeForJS(String txt, boolean useDosCRLF)
	{
		txt = StringUtils.replaceString(txt, "\\", "\\\\");

		if (useDosCRLF)
		{
			txt = StringUtils.replaceString(txt, "\r", "");
			txt = StringUtils.replaceString(txt, "\n", "\\r\\n");
		}
		else
		{
			txt = StringUtils.replaceString(txt, "\r", "");
		}

		txt = StringUtils.replaceString(txt, "\n", "\\n");
		txt = StringUtils.replaceString(txt, "'", "\\'");
		txt = StringUtils.replaceString(txt, "\"", "\\\"");
		return txt;
	}




	public static String escapeForCSV(String sArg)
	{
		StringBuffer sb = new StringBuffer();
		if (sArg != null)
		{
			if ((sArg.indexOf(",") >= 0) || (sArg.indexOf("\n") >= 0) || (sArg.indexOf("\r") >= 0)
					|| (sArg.indexOf("\"") >= 0))
			{
				sb.append("\"");
				sb.append(StringUtils.replaceString(sArg, "\"", "\"\""));
				sb.append("\"");
			}
			else
			{
				sb.append(sArg);
			}
		}

		return sb.toString();
	}

	public static String escapeForHTML(String sArg, boolean isAscii)
	{
		String s;
		if(isAscii)
		{
			s = replaceString(sArg, "&", "&");
			s = replaceString(s, "<", "<");
			s = replaceString(s, ">", ">");
			s = replaceString(s, "\"", "\"");
			s = replaceString(s, "'", "'");
		}
		else
		{
			s = replaceString(sArg,"&","&");
			s = replaceString(s, "<", "<");
			s = replaceString(s, ">", ">");
			s = replaceString(s, "\"", "\"");
			s = replaceString(s, "'", "'");
		}
		return preserveSpaceRuns(s);
	}

	public static String preserveSpaceRuns(String s)
	{
		if (s.indexOf("  ") < 0)        
			// Quick check for no runs of spaces
			return s;
		else
		{
			int imax = s.length();
			StringBuffer sb = new StringBuffer(imax + imax);
			for (int i = 0; i < imax; i++)
			{
				char c = s.charAt(i);
				sb.append(c);
				if (c == ' ')
				{
					for (int j = i + 1; j < imax && s.charAt(j) == c; j++)
					{
						sb.append(" ");
						i = j;
					}
				}
			}
			return sb.toString();
		}
	}



	public static String escapeWithHTMLEntities(String s, int beg, int end)
	{
		StringBuffer sbResult = new StringBuffer();

		for (int i = 0; i < s.length(); i++)
		{
			int ch = (int)s.charAt(i);
			if (ch < beg || ch > end)
				sbResult.append("&#" + ch + ";");
			else
				sbResult.append(s.charAt(i));
		}

		return sbResult.toString();
	}

	public static String getTrimWhiteSpaces(String text)
	{
		String result = "";

		if (text != null)
		{
			result = text.trim();
		}

		return result;
	}

	public static String getStringByPosition(String msg, int position) 
	{	
		String result = "";

		if(msg != null && msg.contains(":")) {
			String[] temp = msg.split(":");
			result = temp[position];
		} else {
			result = msg;
		}
		return result;
	}   

	public static String getReverseLoginStatusLookup(String loginStatus)
	{
		String result = null;

		if (loginStatus != null)
		{
			if (loginStatus.equals("Logout"))
			{
				result = "Login";
			}
			else
			{
				result = "Logout";
			}
		}

		return result;
	}

	public static String getTextNormalized(String str)
	{
		return (str == null)?"":str;
	}

	public static String getLookupURL(String page, String text)
	{
		Tag a = new Tag("a", "href=" + page + "[param]");
		a.add(text);
		String myURL = a.toString();
		return myURL;
	}

	public static String getJavascriptLookupURL(String text)
	{
		text = getLinkString(text);

		Tag a = new Tag("a", "href=\"javascript:postValueToParent('[param]')\"");
		a.add(text);
		String myURL = a.toString();
		return myURL;
	}

	public static String getPopupURL(String pid, String text)
	{	
		text = (text == null)?"":text;
		
		String popupPage = "process_monitor_status_details.jsp?opt=" + text + "&pid=" + pid;
		
		Tag a = new Tag("a", "href=\"javascript: void(0)\" onclick=\"popup('" + popupPage + "')\"" );
		a.add(text);
		String myURL = a.toString();
		return myURL;
	}
	
	public static String getPopupURL(String text)
	{	
		text = (text == null)?"":text;
		
		String popupPage = "process_monitor_status_details.jsp?opt=" + text;
		
		Tag a = new Tag("a", "href=\"javascript: void(0)\" onclick=\"popup('" + popupPage + "')\"" );
		a.add(text);
		String myURL = a.toString();
		return myURL;
	}

	public static String getInsertionString(String text)
	{
		String result = " ";

		if (text != null)
		{
			result = text.trim();
			if (result.equals(""))
			{
				result = " ";
			}
		}

		return result;
	}

	public static String getUpdateString(String text)
	{
		String result = " ";

		if (text != null)
		{
			result = text.trim();
			if (result.equals(""))
			{
				result = " ";
			}
		}

		return result;
	}

	public static java.sql.Date getInsertionDate(String dateValue)
	{
		// Assume the date format string is "MM/DD/YYYY";
		// If argument is empty, return today date.
		// assume date string is not null

		Calendar calendar = Calendar.getInstance();
		java.sql.Date result = new java.sql.Date(calendar.getTime().getTime());

		if (dateValue != null)
		{
			dateValue = dateValue.trim();
			if (!dateValue.equals(""))
			{
				try 
				{
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					java.util.Date utilDate = formatter.parse(dateValue);
					result = new java.sql.Date(utilDate.getTime());
				} 
				catch (ParseException e) 
				{

				}
			}
		}

		return result;

	}

	public static java.sql.Date getInsertionDateFormat(String dateValue, String format)
	{
		// Assume the date format string is "YYYY-MM-DD";
		// If argument is empty, return today date.
		// assume date string is not null

		Calendar calendar = Calendar.getInstance();
		java.sql.Date result = new java.sql.Date(calendar.getTime().getTime());

		format = getTrimWhiteSpaces(format);
		
		if (dateValue != null)
		{
			dateValue = dateValue.trim();
			if (!dateValue.equals(""))
			{
				try 
				{
					SimpleDateFormat formatter = new SimpleDateFormat(format);
					java.util.Date utilDate = formatter.parse(dateValue);
					result = new java.sql.Date(utilDate.getTime());
				} 
				catch (ParseException e) 
				{

				}
			}
		}

		return result;

	}
	
	public static java.sql.Date getInsertionDateOtherFormat(String dateValue)
	{
		// Assume the date format string is "YYYY-MM-DD";
		// If argument is empty, return today date.
		// assume date string is not null

		Calendar calendar = Calendar.getInstance();
		java.sql.Date result = new java.sql.Date(calendar.getTime().getTime());

		if (dateValue != null)
		{
			dateValue = dateValue.trim();
			if (!dateValue.equals(""))
			{
				try 
				{
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date utilDate = formatter.parse(dateValue);
					result = new java.sql.Date(utilDate.getTime());
				} 
				catch (ParseException e) 
				{

				}
			}
		}

		return result;

	}

	public static java.sql.Date getUpdateDateOtherFormat(String dateValue)
	{
		// Assume the date format string is "YYYY-MM-DD";
		// If argument is empty, return today date.
		// assume date string is not null

		Calendar calendar = Calendar.getInstance();
		java.sql.Date result = new java.sql.Date(calendar.getTime().getTime());

		if (dateValue != null)
		{
			dateValue = dateValue.trim();
			if (!dateValue.equals(""))
			{
				try 
				{
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date utilDate = formatter.parse(dateValue);
					result = new java.sql.Date(utilDate.getTime());
				} 
				catch (ParseException e) 
				{

				}
			}
		}

		return result;

	}

	public static java.sql.Date getUpdateDateFormat(String dateValue, String format)
	{
		// Assume the date format string is "yyyy-M-dd";
		// If argument is empty, return today date.
		// assume date string is not null

		Calendar calendar = Calendar.getInstance();
		java.sql.Date result = new java.sql.Date(calendar.getTime().getTime());

		format = getTrimWhiteSpaces(format);
		
		if (dateValue != null)
		{
			dateValue = dateValue.trim();
			if (!dateValue.equals(""))
			{
				try 
				{
					SimpleDateFormat formatter = new SimpleDateFormat(format);
					java.util.Date utilDate = formatter.parse(dateValue);
					result = new java.sql.Date(utilDate.getTime());
				} 
				catch (ParseException e) 
				{

				}
			}
		}

		return result;

	}
	
	public static java.sql.Date getUpdateDate(String dateValue)
	{
		// Assume the date format string is "MM/DD/YYYY";
		// If argument is empty, return today date.
		// assume date string is not null

		Calendar calendar = Calendar.getInstance();
		java.sql.Date result = new java.sql.Date(calendar.getTime().getTime());

		if (dateValue != null)
		{
			dateValue = dateValue.trim();
			if (!dateValue.equals(""))
			{
				try 
				{
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					java.util.Date utilDate = formatter.parse(dateValue);
					result = new java.sql.Date(utilDate.getTime());
				} 
				catch (ParseException e) 
				{

				}
			}
		}

		return result;

	}

	public static String getFirstCharacterRemove(String str)
	{
		String result = "";

		if (str != null)
		{
			result = str.substring(1);
		}

		return result;
	}

	public static String getDigitString(String str)
	{
		String result = "";

		if (str != null)
		{
			result = str.replaceAll("[^0-9]", "");
		}

		return result;
	}

	public static String getLinkString(String str)
	{
		String result = "(blank)";

		if (str != null)
		{
			str = str.trim();

			if (!str.equals(""))
			{
				result = str;
			}
		}

		return result;
	}
	
	public static String getReportTypeDescription(String reportType)
	{
		String result = "";
	
		if (reportType != null)
		{
			if (reportType.equals("WKC"))
			{
				result = "Worker Comp";
			}
			else if (reportType.equals("NDI"))
			{
				result = "IDL/NDI";
			}
			else
			{
				result = reportType; 
			}
		}
		
		return result;
	}
	
	public static String getCurrentDateFormat(String dateFormat)
	{
		String result = "";

		Date now = new Date();
	
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			result = sdf.format(now);
		}
		catch (Exception e)
		{
			result = now.toString();
			result = result.replaceAll(" ", "_");
		}
		
		return result;

	}
	
}