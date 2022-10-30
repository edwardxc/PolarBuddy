import json

import requests
import requests
import msal
import pandas as pd

df = pd.read_excel('/Users/edwardxc/git/id.xlsx')

client_id = df.iloc[0].iloc[0]
client_secret = df.iloc[1].iloc[0]
tenant_id = df.iloc[2].iloc[0]
print("client_id" + client_id)

authority = f"https://login.microsoftonline.com/{tenant_id}"

app = msal.ConfidentialClientApplication(
    client_id=client_id,
    authority="https://login.microsoftonline.com/bowdoin.onmicrosoft.com", client_credential=client_secret, )

# app = PublicClientApplication(
#     client_id,
#     authority="https://login.microsoftonline.com/bowdoin.onmicrosoft.com")

# from msal import PublicClientApplication
# app = PublicClientApplication(
#     client_id,
#     authority="https://login.microsoftonline.com/984e32e5-f98a-4600-aa32-27c3f948abe3")

scopes = ["https://graph.microsoft.com/.default"]
# scopes = ["https://graph.microsoft.com/Mail.Send/.default"]
result = None
result = app.acquire_token_silent(scopes, account=None)

if not result:
    print(
        "No suitable token exists in cache. Let's get a new one from Azure Active Directory.")
    # result = app.acquire_token_by_username_password("cxing@bowdoin.edu","1Edwardxc123",scopes)
    result = app.acquire_token_for_client(scopes=scopes)

# if "access_token" in result:
#     print("Access token is " + result["access_token"])

if "access_token" in result:
    userId = "cxing@bowdoin.edu"
    endpoint = f'https://graph.microsoft.com/v1.0/users/{userId}/sendMail'
    toUserEmail = "edwardxc@outlook.com"
    email_msg = {'Message': {'Subject': "Test Sending Email from Python",
                             'Body': {'ContentType': 'Text', 'Content': "This is a test email."},
                             'ToRecipients': [{'EmailAddress': {'Address': toUserEmail}}]
                             },
                 'SaveToSentItems': 'true'}
    r = requests.post(endpoint,
                      headers={'Authorization': 'Bearer ' + result['access_token']}, json=email_msg)
    if r.ok:
        print('Sent email successfully')
    else:
        print(r.json())
else:
    print(result.get("error"))
    print(result.get("error_description"))
    print(result.get("correlation_id"))



## Useful pages
## https://github.com/microsoftgraph/msgraph-sdk-java
## https://learn.microsoft.com/en-us/python/api/overview/azure/active-directory?view=azure-python
## https://learn.microsoft.com/en-us/azure/active-directory/develop/scenario-web-app-call-api-acquire-token?tabs=python
## https://learn.microsoft.com/en-us/answers/questions/843875/access-to-office365-mailbox-for-a-java-application.html
## https://portal.azure.com/#home
## https://learn.microsoft.com/en-us/python/api/msal/msal.application.confidentialclientapplication?view=azure-python
## https://helpdesk.kaseya.com/hc/en-gb/articles/115002521251-How-Do-I-Find-My-Azure-AD-Tenant-Name-
