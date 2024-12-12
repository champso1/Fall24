#!/usr/bin/env python3

import glob
import os
from argparse import ArgumentParser

arg = ArgumentParser()
arg.add_argument("path")
args = arg.parse_args()
PATH = args.path

path = os.path.abspath(PATH) + "/./**/*.docx"

files_before = glob.glob(path, recursive=True)
files_before = ["\\ ".join(file.split(' ')) for file in files_before]
#print(files_before)

files_after = [file.split(".docx")[0]+".pdf" for file in files_before]
files_after = ["_".join(file.split("\\ ")) for file in files_before]
#print(files_after)



for i in range(len(files_before)):
    file_before = files_before[i]
    file_after = files_after[i]
    os.system(f"pandoc -f docx -t pdf -o {file_after} {file_before}")
    #print(f"pandoc -f docx -t pdf -o {file_after} {file_before}")
    os.system(f"rm -f {file_before}")
