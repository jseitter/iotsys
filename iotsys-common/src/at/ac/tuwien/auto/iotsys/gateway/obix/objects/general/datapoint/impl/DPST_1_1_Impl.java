/*******************************************************************************
 * Copyright (c) 2013
 * Institute of Computer Aided Automation, Automation Systems Group, TU Wien.
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the Institute nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE INSTITUTE AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE INSTITUTE OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * 
 * This file is part of the IoTSyS project.
 ******************************************************************************/

package at.ac.tuwien.auto.iotsys.gateway.obix.objects.general.datapoint.impl;

import java.util.logging.Logger;

import obix.Bool;
import obix.Contract;
import obix.Enum;
import obix.Int;
import obix.Obj;
import obix.Real;
import obix.Uri;
import at.ac.tuwien.auto.iotsys.gateway.obix.objects.general.datapoint.DPST_1_1;
import at.ac.tuwien.auto.iotsys.gateway.obix.objects.general.datapoint.DPT_1;
import at.ac.tuwien.auto.iotsys.gateway.obix.objects.general.datapoint.Datapoint;
import at.ac.tuwien.auto.iotsys.gateway.obix.objects.general.encoding.EncodingOnOff;
import at.ac.tuwien.auto.iotsys.gateway.obix.objects.general.language.Multilingual;

public class DPST_1_1_Impl extends DPT_1_Impl implements DPST_1_1
{
	private static final Logger log = Logger.getLogger(DPST_1_1_Impl.class.getName());

	private Enum encoding = new Enum();

	public DPST_1_1_Impl(String name, String displayName, String display, boolean writable)
	{
		super(name, displayName, display, new Contract(new String[] { DPST_1_1.CONTRACT, DPT_1.CONTRACT, Datapoint.CONTRACT, Multilingual.CONTRACT }));
		
		this.value.setWritable(writable);

		this.encoding.setName("encoding");
		this.encoding.setHref(new Uri("encoding"));
		this.encoding.setRange(new Uri(EncodingOnOff.HREF));
		this.add(encoding);
	}

	@Override
	public void writeObject(Obj input)
	{
		if (input instanceof DPST_1_1)
		{
			DPST_1_1 in = (DPST_1_1) input;
			log.info("Writing on data point.");
			this.value.set(in.value().get());
		}
		else if (input instanceof Bool)
		{
			this.value.set(((Bool) input).get());
		}
		else if (input instanceof Real)
		{
			this.value.set(((Real) input).get());
		}
		else if (input instanceof Int)
		{
			this.value.set(((Int) input).get());
		}
	}
	
	public obix.Enum encoding()
	{
		return encoding;
	}

}
